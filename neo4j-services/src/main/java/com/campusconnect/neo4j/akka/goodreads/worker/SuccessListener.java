package com.campusconnect.neo4j.akka.goodreads.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.akka.goodreads.task.AddGoodreadsBookToUserTask;
import com.campusconnect.neo4j.akka.goodreads.task.SaveBookTask;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sn1 on 3/10/15.
 */
public class SuccessListener extends UntypedActor {
    @Autowired
    private GoodreadsAsynchHandler goodreadsAsynchHandler;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof SaveBookTask) {
            goodreadsAsynchHandler.getSaveBooksToDbRouter().tell(message, getSelf());
        } else if (message instanceof AddGoodreadsBookToUserTask) {
            goodreadsAsynchHandler.getAddGoodreadsBookToUserRouter().tell(message, getSelf());
        }
    }
}
