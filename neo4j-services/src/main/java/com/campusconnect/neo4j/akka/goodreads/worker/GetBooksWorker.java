package com.campusconnect.neo4j.akka.goodreads.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.akka.goodreads.client.GoodreadsOauthClient;
import com.campusconnect.neo4j.akka.goodreads.mappers.BookMapper;
import com.campusconnect.neo4j.akka.goodreads.task.GetBooksTask;
import com.campusconnect.neo4j.akka.goodreads.types.GetBooksResponse;
import com.campusconnect.neo4j.akka.goodreads.types.Review;
import com.campusconnect.neo4j.akka.goodreads.types.Reviews;
import com.campusconnect.neo4j.akka.goodreads.util.ResponseUtils;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.types.common.GoodreadsStatus;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.ReadRelationship;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.neo4j.WishListRelationship;
import com.sun.jersey.api.uri.UriBuilderImpl;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 3/12/15.
 */
public class GetBooksWorker extends UntypedActor {
    private static Logger logger = LoggerFactory.getLogger(GetBooksWorker.class);

    @Autowired
    private GoodreadsOauthClient goodreadsOauthClient;

    @Autowired
    private GoodreadsAsynchHandler goodreadsAsynchHandler;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof GetBooksTask) {
            GetBooksTask getBooksTask = (GetBooksTask) message;
            UriBuilder uriBuilder = new UriBuilderImpl();
            uriBuilder.path("https://www.goodreads.com");
            uriBuilder.path("review/list.xml");
            uriBuilder.queryParam("v", "2");
            uriBuilder.queryParam("id", getBooksTask.getGoodreadsUserId());
            uriBuilder.queryParam("key", goodreadsOauthClient.getsApiKey());
            uriBuilder.queryParam("page", getBooksTask.getPage());
            Token sAccessToken = new Token(getBooksTask.getAccessToken(), getBooksTask.getAccessTokenSecret());
            OAuthRequest getBooksRequest = new OAuthRequest(Verb.GET, uriBuilder.build().toString());
            try {
                goodreadsOauthClient.getsService().signRequest(sAccessToken, getBooksRequest);
                Response response = getBooksRequest.send();
                GetBooksResponse getBooksResponse = ResponseUtils.getEntity(response.getBody(), GetBooksResponse.class);
                saveBooksList(getBooksResponse, getBooksTask);
            } catch (Exception e) {
                logger.error("Error occurred while getting books");
            }
        }
    }

    private List<Book> saveBooksList(GetBooksResponse getBooksResponse, GetBooksTask getBooksTask) throws IOException {
        final Reviews reviews = getBooksResponse.getReviews();
        if (Integer.parseInt(reviews.getEnd()) != Integer.parseInt(reviews.getTotal())) {
            getSelf().tell(new GetBooksTask(getBooksTask.getUserId(), getBooksTask.getGoodreadsUserId(), getBooksTask.getPage() + 1,
                    getBooksTask.getAccessToken(), getBooksTask.getAccessTokenSecret()), getSender());
        }

        List<Book> books = new ArrayList<>();
        User user = userDao.getUser(getBooksTask.getUserId());
        if (reviews.getReview() != null)
            for (Review review : reviews.getReview()) {
                Book book = BookMapper.getBookFromGoodreadsBook(review.getBook());
                books.add(book);
//                goodreadsAsynchHandler.getAddGoodreadsBookToUserRouter().tell(new AddGoodreadsBookToUserTask(book,
//                                getBooksTask.getUserId(), review.getShelves() != null && !review.getShelves().isEmpty() ? review.getShelves().get(0).getName() : "none"),
//                        goodreadsAsynchHandler.getSuccessListener());

                Book bookByGoodreadsIdWithUser = bookDao.getBookByGoodreadsIdWithUser(book.getGoodreadsId(), getBooksTask.getUserId());
                if (bookByGoodreadsIdWithUser == null || bookByGoodreadsIdWithUser.getBookType() == null) {
                    Book dbBook = bookDao.getBookByGoodreadsIdAndSaveIfNotExists(book.getGoodreadsId().toString(), book);
//                Book dbBook = bookDao.getBookByGoodreadsId(book.getGoodreadsId().toString());
                    //todo: dont create a relation if already exists
                    final Long now = System.currentTimeMillis();
                    String shelf = review.getShelves() != null && !review.getShelves().isEmpty() ? review.getShelves().get(0).getName() : "none";
                    if (shelf.equals(GoodreadsStatus.TO_READ.toString())) {
                        bookDao.addWishBookToUser(new WishListRelationship(user, dbBook, "wish", now, now));
                    } else
                        bookDao.listBookAsRead(new ReadRelationship(user, dbBook, null, now, now, shelf));
                }
            }
        if (Integer.parseInt(reviews.getEnd()) == Integer.parseInt(reviews.getTotal())) {
            logger.info("Firing for user rec for wishlist");
            goodreadsAsynchHandler.getFriendRecForUser(user.getId(), user.getGoodreadsId(), user.getGoodreadsAccessToken(), user.getGoodreadsAccessTokenSecret());
            updateGoodreadsSynchStatusToDone(user);
        }
        return books;
    }

    private void updateGoodreadsSynchStatusToDone(User user) {
        user.setGoodReadsSynchStatus("done");
        user.setLastGoodreadsSychDate(System.currentTimeMillis());
        userDao.updateUser(user.getId(), user);
    }
}
