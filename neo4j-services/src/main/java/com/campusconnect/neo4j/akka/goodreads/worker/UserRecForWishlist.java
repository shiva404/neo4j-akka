package com.campusconnect.neo4j.akka.goodreads.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.goodreads.api.GetBooks;
import com.campusconnect.neo4j.akka.goodreads.mappers.BookMapper;
import com.campusconnect.neo4j.akka.goodreads.task.UserRecForWishListTask;
import com.campusconnect.neo4j.akka.goodreads.types.GetBooksResponse;
import com.campusconnect.neo4j.akka.goodreads.types.Review;
import com.campusconnect.neo4j.akka.goodreads.types.Reviews;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.mappers.WebToNeo4jMapper;
import com.campusconnect.neo4j.types.common.GoodreadsStatus;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.GoodreadsRecRelationship;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.GoodreadsUserRecommendation;
import com.campusconnect.neo4j.types.web.WishListBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 3/24/15.
 */
public class UserRecForWishlist extends UntypedActor {
    public static Logger logger = LoggerFactory.getLogger(UserRecForWishlist.class);
    @Autowired
    GetBooks getBooks;

    @Autowired
    BookDao bookDao;

    @Autowired
    UserDao userDao;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof UserRecForWishListTask) {
            UserRecForWishListTask task = (UserRecForWishListTask) message;
            

            //Get books for user friend
            GetBooksResponse getBooksResponse = getBooks.getBooksForUser(task.getFriend().getId(), task.getPage());
            if (getBooksResponse != null) {
                final Reviews reviews = getBooksResponse.getReviews();
                List<Book> books = new ArrayList<>();
                if (reviews != null && reviews.getReview() != null) {

                    //If more books present the message to fetch the books back
                    if (Integer.parseInt(reviews.getEnd()) != Integer.parseInt(reviews.getTotal())) {
                        getSelf().tell(new UserRecForWishListTask(task.getAccessToken(), task.getAccessSecret(),
                                task.getUserId(), task.getGoodreadsId(), task.getFriend(), task.getWishListBooks(),
                                task.getPage() + 1, task.getGoodreadsUserRecommendations()), getSender());
                    }

                    //prepare books list
                    for (Review review : reviews.getReview()) {
                        if (review.getShelves() != null && !review.getShelves().isEmpty() && !review.getShelves().get(0).getName().equals(GoodreadsStatus.TO_READ.toString())) {
                            Book book = BookMapper.getBookFromGoodreadsBook(review.getBook());
                            books.add(book);
                        }
                    }
                }

                //See what all books need to rec with user
                List<WishListBook> wishListBooks = task.getWishListBooks();
                if (wishListBooks != null)
                    if(!books.isEmpty() && !wishListBooks.isEmpty())
                    for (Book friendBook : books) {
                        for (com.campusconnect.neo4j.types.web.Book wishListBook : wishListBooks) {
                            if (wishListBook.getGoodreadsId() != null && wishListBook.getGoodreadsId().equals(friendBook.getGoodreadsId())) {
                                if (recExists(wishListBook.getGoodreadsId(), task.getFriend().getId(), task.getGoodreadsUserRecommendations())) {
                                    logger.info("User Recommendation already exists:");
                                } else {
                                    User user = userDao.getUser(task.getUserId());
                                    final String goodreadsId = task.getFriend().getId();
                                    User friend = userDao.getUserByGoodreadsId(goodreadsId);
                                    bookDao.createGoodreadsFriendBookRec(new GoodreadsRecRelationship(user, WebToNeo4jMapper.mapBookWebToNeo4j(wishListBook), "rec", friend != null ? friend.getId() : null, goodreadsId, task.getFriend().getImageUrl(), task.getFriend().getName()));
                                }
                            }
                        }
                    }
            }
        }
    }

    private boolean recExists(Integer goodreadsBookId, String friendId, List<GoodreadsUserRecommendation> goodreadsUserRecommendations) {
        for (GoodreadsUserRecommendation goodreadsUserRecommendation : goodreadsUserRecommendations) {
            if (goodreadsUserRecommendation.getBook().getGoodreadsId().equals(goodreadsBookId) && goodreadsUserRecommendation.getFriendGoodreadsId().equals(friendId)) {
                return true;
            }
        }
        return false;
    }
}
