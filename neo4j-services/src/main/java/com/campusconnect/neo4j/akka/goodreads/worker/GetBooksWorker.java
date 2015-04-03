package com.campusconnect.neo4j.akka.goodreads.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.akka.goodreads.util.ResponseUtils;
import com.campusconnect.neo4j.akka.goodreads.client.GoodreadsOauthClient;
import com.campusconnect.neo4j.akka.goodreads.mappers.BookMapper;
import com.campusconnect.neo4j.akka.goodreads.task.AddGoodreadsBookToUserTask;
import com.campusconnect.neo4j.akka.goodreads.task.GetBooksTask;
import com.campusconnect.neo4j.akka.goodreads.types.GetBooksResponse;
import com.campusconnect.neo4j.akka.goodreads.types.Review;
import com.campusconnect.neo4j.akka.goodreads.types.Reviews;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.sun.jersey.api.uri.UriBuilderImpl;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 3/12/15.
 */
public class GetBooksWorker extends UntypedActor {
    
    @Autowired
    private GoodreadsOauthClient goodreadsOauthClient;
    
    @Autowired
    private GoodreadsAsynchHandler goodreadsAsynchHandler;
    
    @Autowired
    private BookDao bookDao;
    
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof GetBooksTask) {
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
            goodreadsOauthClient.getsService().signRequest(sAccessToken, getBooksRequest);
            Response response = getBooksRequest.send();
            GetBooksResponse getBooksResponse = ResponseUtils.getEntity(response.getBody(), GetBooksResponse.class);
            List<com.campusconnect.neo4j.types.Book> books = getBooksList(getBooksResponse, getBooksTask);
            System.out.println(response.getBody());
            //todo: return books - save to cache?
        }
    }

    private List<com.campusconnect.neo4j.types.Book> getBooksList(GetBooksResponse getBooksResponse, GetBooksTask getBooksTask) throws IOException {
        final Reviews reviews = getBooksResponse.getReviews();
        if(Integer.parseInt(reviews.getEnd()) != Integer.parseInt(reviews.getTotal())){
            getSelf().tell(new GetBooksTask(getBooksTask.getUserId(), getBooksTask.getGoodreadsUserId(), getBooksTask.getPage() + 1,
                getBooksTask.getAccessToken(), getBooksTask.getAccessTokenSecret()), getSender());
        }
        List<com.campusconnect.neo4j.types.Book> books = new ArrayList<>();
        if(reviews.getReview() != null)
            for (Review review : reviews.getReview()) {
                com.campusconnect.neo4j.types.Book book = BookMapper.getBookFromGoodreadsBook(review.getBook());
                books.add(book);
                goodreadsAsynchHandler.getAddGoodreadsBookToUserRouter().tell(new AddGoodreadsBookToUserTask(book,
                                getBooksTask.getUserId(), review.getShelves() != null && !review.getShelves().isEmpty() ? review.getShelves().get(0).getName() : "none"),
                        goodreadsAsynchHandler.getSuccessListener());
            }
        return books;
    }
}
