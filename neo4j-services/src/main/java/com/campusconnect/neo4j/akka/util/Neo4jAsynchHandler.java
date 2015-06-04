package com.campusconnect.neo4j.akka.util;

import akka.actor.ActorRef;
import com.campusconnect.neo4j.akka.util.task.AcceptionToLendBookTask;
import com.campusconnect.neo4j.akka.util.task.BorrowBookInitEmailTask;
import com.campusconnect.neo4j.akka.util.task.FriendRequestEmailTask;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.User;

/**
 * Created by sn1 on 5/1/15.
 */
public class Neo4jAsynchHandler {
    private ActorRef borrowBookEmailSender;
    private ActorRef acceptToLendBookEmailSender;
    private ActorRef successListener;
    private ActorRef friendRequestEmailSemder;

    public Neo4jAsynchHandler(ActorRef borrowBookEmailSender, ActorRef successListener, ActorRef acceptToLendBookEmailSender, ActorRef friendRequestEmailSemder) {
        this.borrowBookEmailSender = borrowBookEmailSender;
        this.successListener = successListener;
        this.acceptToLendBookEmailSender = acceptToLendBookEmailSender;
        this.friendRequestEmailSemder = friendRequestEmailSemder;
    }

    public void sendAcceptToLendBookEmail(User owner, User borrower, Book book) {
        acceptToLendBookEmailSender.tell(new AcceptionToLendBookTask(borrower, book, owner), successListener);
    }

    public void sendBorrowInitEmail(User fromUser, User toUser, Book book) {
        borrowBookEmailSender.tell(new BorrowBookInitEmailTask(toUser, book, fromUser), successListener);
    }

    public void sendFriendRequestEmail(User fromUser, User toUser) {
        friendRequestEmailSemder.tell(new FriendRequestEmailTask(fromUser, toUser), successListener);
    }
}
