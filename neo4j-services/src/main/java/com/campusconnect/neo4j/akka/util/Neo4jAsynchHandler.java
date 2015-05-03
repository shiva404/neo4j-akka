package com.campusconnect.neo4j.akka.util;

import akka.actor.ActorRef;

import com.campusconnect.neo4j.akka.util.task.AcceptionToLendBookTask;
import com.campusconnect.neo4j.akka.util.task.BorrowBookInitEmailTask;
import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.User;

/**
 * Created by sn1 on 5/1/15.
 */
public class Neo4jAsynchHandler {
    public Neo4jAsynchHandler(ActorRef borrowBookEmailSender, ActorRef successListener,ActorRef acceptToLendBookEmailSender) {
        this.borrowBookEmailSender = borrowBookEmailSender;
        this.successListener = successListener;
        this.acceptToLendBookEmailSender = acceptToLendBookEmailSender;
    }

    private ActorRef borrowBookEmailSender;
    private ActorRef acceptToLendBookEmailSender;
    private ActorRef successListener;
    
    public void sendAcceptToLendBookEmail(User owner, User borrower, Book book) {
    	acceptToLendBookEmailSender.tell(new AcceptionToLendBookTask(borrower, book, owner), successListener);
    }
    
    public void sendBorrowInitEmail(User fromUser, User toUser, Book book) {
        borrowBookEmailSender.tell(new BorrowBookInitEmailTask(toUser, book, fromUser), successListener);
    }
}
