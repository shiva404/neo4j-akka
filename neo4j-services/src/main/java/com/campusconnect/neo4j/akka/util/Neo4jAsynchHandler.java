package com.campusconnect.neo4j.akka.util;

import akka.actor.ActorRef;

import com.campusconnect.neo4j.akka.util.task.BorrowBookInitEmailTask;
import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.User;

/**
 * Created by sn1 on 5/1/15.
 */
public class Neo4jAsynchHandler {
    public Neo4jAsynchHandler(ActorRef borrowBookEmailSender, ActorRef successListener) {
        this.borrowBookEmailSender = borrowBookEmailSender;
        this.successListener = successListener;
    }

    private ActorRef borrowBookEmailSender;
    private ActorRef successListener;
    
    public void sendBorrowInitEmail(User fromUser, User toUser, Book book) {
        borrowBookEmailSender.tell(new BorrowBookInitEmailTask(toUser, book, fromUser), successListener);
    }
}
