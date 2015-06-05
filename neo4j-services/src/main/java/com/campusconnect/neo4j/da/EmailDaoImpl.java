package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.akka.util.Neo4jAsynchHandler;
import com.campusconnect.neo4j.da.iface.EmailDao;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.User;

/**
 * Created by sn1 on 5/1/15.
 */
public class EmailDaoImpl implements EmailDao {

    private Neo4jAsynchHandler neo4jAsynchHandler;

    public EmailDaoImpl(Neo4jAsynchHandler neo4jAsynchHandler) {
        this.neo4jAsynchHandler = neo4jAsynchHandler;
    }

    @Override
    public void sendBorrowBookInitEmail(User fromUser, User toUser, Book book) {
        neo4jAsynchHandler.sendBorrowInitEmail(fromUser, toUser, book);
    }

    @Override
    public void sendAcceptedToLendBookEmail(User owner, User borrower, Book book) {
        neo4jAsynchHandler.sendAcceptToLendBookEmail(owner, borrower, book);
    }

    @Override
    public void sendFriendRequestEmail(User user, User friend) {

        neo4jAsynchHandler.sendFriendRequestEmail(user, friend);

    }
}
