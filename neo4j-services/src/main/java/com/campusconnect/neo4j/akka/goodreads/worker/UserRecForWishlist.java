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
import com.campusconnect.neo4j.types.*;
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
            UserRecForWishListTask userRecForWishListTask = (UserRecForWishListTask) message;

            //Get books for user friend
            GetBooksResponse getBooksResponse = getBooks.getBooksForUser(userRecForWishListTask.getFriend().getId(), userRecForWishListTask.getPage());
            if (getBooksResponse != null) {
                final Reviews reviews = getBooksResponse.getReviews();
                List<Book> books = new ArrayList<>();
                if (reviews != null && reviews.getReview() != null) {

                    //If more books present the message to fetch the books back
                    if (Integer.parseInt(reviews.getEnd()) != Integer.parseInt(reviews.getTotal())) {
                        getSelf().tell(new UserRecForWishListTask(userRecForWishListTask.getAccessToken(), userRecForWishListTask.getAccessSecret(),
                                userRecForWishListTask.getUserId(), userRecForWishListTask.getGoodreadsId(), userRecForWishListTask.getFriend(), userRecForWishListTask.getWishListBooks(),
                                userRecForWishListTask.getPage() + 1, userRecForWishListTask.getUserRecommendations()), getSender());
                    }

                    //prepare books list
                    for (Review review : reviews.getReview()) {
                        if (review.getShelves() != null && !review.getShelves().isEmpty() && !review.getShelves().get(0).getName().equals(GoodreadsStatus.TO_READ.toString())) {
                            com.campusconnect.neo4j.types.Book book = BookMapper.getBookFromGoodreadsBook(review.getBook());
                            books.add(book);
                        }
                    }
                }

                //See what all books need to rec with user
                List<WishListBook> wishListBooks = userRecForWishListTask.getWishListBooks();
                if (wishListBooks != null)
                    for (Book wishListBook : wishListBooks) {
                        for (Book friendBook : books) {
                            if (wishListBook.getGoodreadsId() != null && wishListBook.getGoodreadsId().equals(friendBook.getGoodreadsId())) {
                                if (recExists(wishListBook.getGoodreadsId(), userRecForWishListTask.getFriend().getId(), userRecForWishListTask.getUserRecommendations())) {
                                    logger.info("User Recommendation already exists:");
                                } else {
                                    User user = userDao.getUser(userRecForWishListTask.getUserId());
                                    final String goodreadsId = userRecForWishListTask.getFriend().getId();
                                    User friend = userDao.getUserByGoodreadsId(goodreadsId);
                                    bookDao.createGoodreadsFriendBookRec(new GoodreadsFriendBookRecRelation(user, wishListBook, "rec", friend != null ? friend.getId() : null, goodreadsId, userRecForWishListTask.getFriend().getImageUrl(), userRecForWishListTask.getFriend().getName()));
                                }
                            }
                        }
                    }
            }
        }
    }

    private boolean recExists(Integer goodreadsBookId, String friendId, List<UserRecommendation> userRecommendations) {
        for (UserRecommendation userRecommendation : userRecommendations) {
            if (userRecommendation.getBook().getGoodreadsId().equals(goodreadsBookId) && userRecommendation.getFriendGoodreadsId().equals(friendId)) {
                return true;
            }
        }
        return false;
    }
}
