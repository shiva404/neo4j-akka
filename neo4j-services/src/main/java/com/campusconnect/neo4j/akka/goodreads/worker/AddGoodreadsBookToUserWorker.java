package com.campusconnect.neo4j.akka.goodreads.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.goodreads.task.AddGoodreadsBookToUserTask;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.types.common.GoodreadsStatus;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.ReadRelationship;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.neo4j.WishListRelationship;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sn1 on 3/13/15.
 */
public class AddGoodreadsBookToUserWorker extends UntypedActor {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AddGoodreadsBookToUserTask) {
            AddGoodreadsBookToUserTask addGoodreadsBookToUserTask = (AddGoodreadsBookToUserTask) message;
            // Book book = bookDao.getBookByGoodreadsIdAndSaveIfNotExists(addGoodreadsBookToUserTask.getBookRelatedUser().getGoodreadsId().toString(), addGoodreadsBookToUserTask.getBookRelatedUser());
            Book book = bookDao.getBookByGoodreadsId(addGoodreadsBookToUserTask.getBook().getGoodreadsId());
            User user = userDao.getUser(addGoodreadsBookToUserTask.getUserId());

            //todo: dont create a relation if already exists

            final Long now = System.currentTimeMillis();
            if (addGoodreadsBookToUserTask.getShelfName().equals(GoodreadsStatus.TO_READ.toString())) {
                bookDao.addWishBookToUser(new WishListRelationship(user, book, "wish", now, now));
            } else
                bookDao.listBookAsRead(new ReadRelationship(user, book, null, now, now, addGoodreadsBookToUserTask.getShelfName()));
        }
    }
}
