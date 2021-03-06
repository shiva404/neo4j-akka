package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.User;

/**
 * Created by sn1 on 5/1/15.
 */
public interface EmailDao {
    public void sendBorrowBookInitEmail(User borrower, User owner, Book book);

    public void sendAcceptedToLendBookEmail(User owner, User borrower, Book book);

    public void sendFriendRequestEmail(User user, User friend);
    
    public void sendRejectedToLendBookEmail(User owner, User borrower, Book book,String message);
    
    public void sendSuccessfulBookTransactionEmail(User owner,User borrower,Book book);

}
