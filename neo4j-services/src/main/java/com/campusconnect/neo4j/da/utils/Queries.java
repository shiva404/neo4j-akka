package com.campusconnect.neo4j.da.utils;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/21/15
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Queries {

    public static final String GET_FRIEND_REC_ONLY_COUNT = "MATCH (currentUser:User { id: {userId}})-[:CONNECTED*2..2]-(friend_of_friend:User) " +
    "WHERE NOT (currentUser)-[:CONNECTED]-(friend_of_friend) " +
    "RETURN friend_of_friend, COUNT(*) as count "+
    "ORDER BY COUNT(*) DESC , friend_of_friend";

    public static final String GET_FRIEND_REC_WITH_FRIENDS = "MATCH (currentUser:User { id: {userId}})-[:CONNECTED]- (friend:User) - [:CONNECTED] - (friend_of_friend:User) " +
            "WHERE NOT (currentUser)-[:CONNECTED]-(friend_of_friend) " +
            "RETURN friend_of_friend, friend " +
            "ORDER BY friend_of_friend";

    public static final String GET_WISHLIST_REC_FRIENDS = "match(user:User {id:{userId}}) - [:WISH]-(book:Book)-[:OWNS {status: \"AVAILABLE\"}]-(friend:User)\n" +
            "WHERE (user)-[:CONNECTED]-(friend)" +
            "return book, friend";

    public static final String GET_WISHLIST_GROUP = "match(user:User {id:{userId}})-[:WISH]-(book:Book)-[:OWNS]-(friend:User)-[:USER_ACCESS]-(group:Group) " +
            "WHERE (user)-[:USER_ACCESS]-(group) " +
            "return book,friend,group";


}
