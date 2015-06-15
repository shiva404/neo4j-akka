package com.campusconnect.neo4j.akka.goodreads;

import akka.actor.ActorRef;
import com.campusconnect.neo4j.akka.goodreads.task.*;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.web.WishListBook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by sn1 on 3/10/15.
 */
public class GoodreadsAsynchHandler {

    @Autowired
    BookDao bookDao;
    @Autowired
    UserDao userDao;
    private ActorRef saveBooksToDbRouter;
    private ActorRef getAndSaveBooksRouter;
    private ActorRef successListener;
    private ActorRef addGoodreadsBookToUserRouter;
    private ActorRef friendsBookSearchForWishListRouter;
    private ActorRef getFriendsRouter;
    private ActorRef userRecForWishListRouter;
    private ActorRef addGoodReadsFriendsRouter;
    private ActorRef replaceGRIdWithUserIdRouter;

    public ActorRef getReplaceGRIdWithUserIdRouter() {
        return replaceGRIdWithUserIdRouter;
    }

    public void setReplaceGRIdWithUserIdRouter(ActorRef replaceGRIdWithUserIdRouter) {
        this.replaceGRIdWithUserIdRouter = replaceGRIdWithUserIdRouter;
    }

    public ActorRef getAddGoodReadsFriendsRouter() {
        return addGoodReadsFriendsRouter;
    }

    public void setAddGoodReadsFriendsRouter(ActorRef addGoodReadsFriendsRouter) {
        this.addGoodReadsFriendsRouter = addGoodReadsFriendsRouter;
    }

    public ActorRef getUserRecForWishListRouter() {
        return userRecForWishListRouter;
    }

    public void setUserRecForWishListRouter(ActorRef userRecForWishListRouter) {
        this.userRecForWishListRouter = userRecForWishListRouter;
    }

    public ActorRef getFriendsBookSearchForWishListRouter() {
        return friendsBookSearchForWishListRouter;
    }

    public void setFriendsBookSearchForWishListRouter(ActorRef friendsBookSearchForWishListRouter) {
        this.friendsBookSearchForWishListRouter = friendsBookSearchForWishListRouter;
    }

    public ActorRef getGetFriendsRouter() {
        return getFriendsRouter;
    }

    public void setGetFriendsRouter(ActorRef getFriendsRouter) {
        this.getFriendsRouter = getFriendsRouter;
    }

    public ActorRef getSaveBooksToDbRouter() {
        return saveBooksToDbRouter;
    }

    public void setSaveBooksToDbRouter(ActorRef saveBooksToDbRouter) {
        this.saveBooksToDbRouter = saveBooksToDbRouter;
    }

    public ActorRef getGetAndSaveBooksRouter() {
        return getAndSaveBooksRouter;
    }

    public void setGetAndSaveBooksRouter(ActorRef getAndSaveBooksRouter) {
        this.getAndSaveBooksRouter = getAndSaveBooksRouter;
    }

    public ActorRef getSuccessListener() {
        return successListener;
    }

    public void setSuccessListener(ActorRef successListener) {
        this.successListener = successListener;
    }

    public ActorRef getAddGoodreadsBookToUserRouter() {
        return addGoodreadsBookToUserRouter;
    }

    public void setAddGoodreadsBookToUserRouter(ActorRef addGoodreadsBookToUserRouter) {
        this.addGoodreadsBookToUserRouter = addGoodreadsBookToUserRouter;
    }

    public void saveBook(Book book) {
        saveBooksToDbRouter.tell(new SaveBookTask(book), successListener);
    }

    public void getAndSaveBooks(String userId, String goodreadsUserId, String accessToken, String accessTokenSecret) {
        getAndSaveBooksRouter.tell(new GetBooksTask(userId, goodreadsUserId, 1, accessToken, accessTokenSecret), successListener);
    }

    public void addGoodreadsBookToUser(String userId, Book book, String shelfName) {
        addGoodreadsBookToUserRouter.tell(new AddGoodreadsBookToUserTask(book, userId, shelfName), successListener);
    }

    public void getFriendRecForUser(String userId, String goodreadsUserId, String accessToken, String accessTokenSecret) {
        List<WishListBook> wishListBooks = bookDao.getWishListBooks(userId);
        friendsBookSearchForWishListRouter.tell(new FriendsBookSearchForWishListTask(accessToken, accessTokenSecret, userId, goodreadsUserId, 1, wishListBooks), successListener);
    }

    public void replaceGoodreadsIdWithUserId(String id, Integer goodreadsId, String profileImageUrl) {
        replaceGRIdWithUserIdRouter.tell(new ReplaceGRIdWithUserIdForRecTask(id, goodreadsId, profileImageUrl), successListener);
    }
}
