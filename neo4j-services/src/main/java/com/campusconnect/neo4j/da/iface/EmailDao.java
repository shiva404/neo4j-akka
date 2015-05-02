package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.User;

/**
 * Created by sn1 on 5/1/15.
 */
public interface EmailDao {
    public void sendBorrowBookInitEmail(User fromUser, User toUser, Book book);
}
