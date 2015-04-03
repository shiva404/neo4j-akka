package com.campusconnect.neo4j.akka.goodreads.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.goodreads.task.SaveBookTask;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.types.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sn1 on 3/10/15.
 */
public class SaveBooksToDbWorker extends UntypedActor {
    private static Logger logger = LoggerFactory.getLogger(SaveBooksToDbWorker.class);
    
    @Autowired
    private BookDao bookDao;
    
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof SaveBookTask) {
            logger.debug("Saving book triggered");
            SaveBookTask saveBookTask = (SaveBookTask) message;
            Book createdBook = bookDao.createBook(saveBookTask.getBook());
            logger.debug("created book with Id: " + createdBook);
            getSender().tell(createdBook, getSelf());
        } else {
            unhandled(message);
        }
    }
    
}
