package com.campusconnect.neo4j.da.utils;

import static com.campusconnect.neo4j.util.Constants.AVAILABLE;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/21/15
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Queries {
    public static final String GET_FRIEND_REC_ONLY_COUNT = "MATCH " +
            "(currentUser:User { id: {userId}})-[:CONNECTED*2..2]-(friend_of_friend:User) " +
            "WHERE NOT (currentUser)-[:CONNECTED]-(friend_of_friend) " +
            "RETURN friend_of_friend, COUNT(*) as count " +
            "ORDER BY COUNT(*) DESC , friend_of_friend";

    public static final String GET_FRIEND_REC_WITH_FRIENDS = "MATCH " +
            "(currentUser:User { id: {userId}})-[:CONNECTED]- (friend:User) - [:CONNECTED] - (friend_of_friend:User) " +
            "WHERE NOT (currentUser)-[:CONNECTED]-(friend_of_friend) " +
            "RETURN friend_of_friend, friend " +
            "ORDER BY friend_of_friend";

    public static final String GET_WISHLIST_REC_FRIENDS = "match" +
            "(user:User {id:{userId}}) - [:WISH]-(book:Book)-[:OWNS {status: \"AVAILABLE\"}]-(friend:User)\n" +
            "WHERE (:User {id:{loggedInUser}})-[:CONNECTED {type:\"FRIEND\"}]-(friend)" +
            "return book, friend";

    public static final String GET_WISHLIST_GROUP = "match" +
            "(user:User {id:{userId}})-[:WISH]-(book:Book)-[:OWNS {status: \"AVAILABLE\"}]-(friend:User)-[:USER_ACCESS]-(group:Group) " +
            "WHERE (:User {id:{loggedInUser}})-[:USER_ACCESS]-(group) " +
            "return book,friend,group";

    public static final String GET_OWNED_BOOKS_REC_FRIENDS_WITH_OWNED = "match " +
            "(:User {id:{userId}}) - [ownsRel:OWNS]-(book:Book)-[:OWNS {status: \"AVAILABLE\"}]-(friend:User) " +
            "WHERE (:User {id:{loggedInUser}})-[:CONNECTED {type:\"FRIEND\"}]-(friend) " +
            "return book, friend, ownsRel";

    public static final String GET_OWNED_BOOKS_REC_FRIENDS_WITH_WISH = "match " +
            "(:User {id:{userId}}) - [ownsRel:OWNS]-(book:Book)-[:WISH]-(friend:User) " +
            "WHERE (:User {id:{loggedInUser}})-[:CONNECTED {type:\"FRIEND\"}]-(friend) " +
            "return book, friend, ownsRel";

    public static final String GET_OWNED_BOOKS_REC_GROUP = "match" +
            "(:User {id:{userId}}) - [ownsRel:OWNS]-(book:Book)-[:WISH]-(friend:User)-[:USER_ACCESS]-(group:Group) " +
            "WHERE (:User {id:{loggedInUser}})-[:USER_ACCESS]-(group) " +
            "return book,friend,group,ownsRel";



    public static final String OWNED_BOOKS_BOOKS = "match " +
            "(users:User {id: {userId}})-[relation:OWNS]->(books:Book) " +
            "return books, relation";
    public static final String AVAILABLE_BOOKS_QUERY = "match " +
            "(users:User {id: {userId}})-[relation:OWNS {status: \"" + AVAILABLE + "\"}]->(books:Book) " +
            "return books, relation";

    public static final String LENT_BOOKS_QUERY = "match " +
            "(users:User {id: {userId}})-[relation:OWNS {status: \"lent\"}]->(books:Book) " +
            "return books, relation";
    public static final String READ_BOOKS_QUERY = "match " +
            "(users:User {id: {userId}})-[:READ]->(books:Book) " +
            "return books";
    public static final String WISHLIST_BOOKS_QUERY = "match " +
            "(users:User {id: {userId}})-[relation:WISH]->(books:Book) " +
            "return books, relation";
    public static final String GOODREADS_USER_REC_QUERY = "match " +
            "(users:User {id: {userId}})-[relation:GR_REC]->(books:Book) " +
            "return books, relation";
    public static final String GET_BOOK_BY_GRID_USER_QUERY = "match" +
            "(book:Book {goodreadsId: {goodreadsId}}) - [relation] - (user:User {id: {userId}}) " +
            "return relation, book";

    public static final String GET_ALL_BOOKS_QUERY = "match" +
            "(book:Book) - [relation] - (user:User {id: {userId}}) " +
            "return relation, book";
    public static final String BORROWED_BOOKS_QUERY = "match " +
            "(users:User {id: {userId}})-[relation:BORROWED]->(books:Book) " +
            "return books, relation";
    public static final String CURRENTLY_READING_BOOKS_QUERY = "match " +
            "(users:User {id: {userId}})-[relation:CURRENTLY_READING]->(books:Book) " +
            "return books, relation";

    public static final String GET_LOOKING_FOR_FRIENDS_FOR_OWNED_BOOK = "match " +
            "(user:User {id:{userId}}) - [:OWNS {status:\"AVAILABLE\"}] - (book:Book {id:{bookId}}) - [:WISH] - (friend:User) " +
            "return friend";

}
