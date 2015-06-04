package com.campusconnect.neo4j.mappers;

import com.campusconnect.neo4j.types.neo4j.Address;
import com.campusconnect.neo4j.types.web.*;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/4/15
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Neo4jToWebMapper {
    public User mapUserNeo4jToWeb(com.campusconnect.neo4j.types.neo4j.User user) {
        return new User(user.getAddresses(), user.getCreatedDate(), user.getEmail(), user.getFavorites(), user.getFbId(), user.getGoodreadsAccessToken(), user.getGoodreadsAccessTokenSecret(), user.getGoodreadsAuthStatus(),
                user.getGoodReadsSynchStatus(), user.getLastGoodreadsSychDate(), user.getGoodreadsId(), user.getGoogleId(), user.getId(), user.getLastModifiedDate(), user.getName(), user.getPhone(), user.getGender(),
                user.getProfileImageUrl(), user.getWorkDesignation(), user.getWorkLocation(), user.getUserRelation());
    }

    public Book mapBookNeo4jToWeb(com.campusconnect.neo4j.types.neo4j.Book book) {
        return new Book(book.getId(), book.getGoodreadsId(), book.getAuthorName(), book.getGoodreadsAuthorId(), book.getName(), book.getIsbn(), book.getIsbn13(),
                book.getPublishedYear(), book.getDescription(), book.getPublisher(), book.getNumberOfPages(), book.getImageUrl());
    }

    public Favourite mapFavouriteNeo4jToWeb(com.campusconnect.neo4j.types.neo4j.Favourite favourite) {
        return new Favourite(favourite.getGenre(), favourite.getNodeId());
    }

    public Group mapGroupNeo4jToWeb(com.campusconnect.neo4j.types.neo4j.Group group) {
        return new Group(group.getId(), group.getName(), group.getCreatedDate(), group.getLastModifiedTime(), group.getLastModifiedBy());
    }

    public Reminder mapReminderNeo4jToWeb(com.campusconnect.neo4j.types.neo4j.Reminder reminder) {
        return new Reminder(reminder.getCreatedDate(), reminder.getDescription(), reminder.getLastModifiedTime(), reminder.getNodeId(), reminder.getReminderMessage(), reminder.getReminderTime(), reminder.getSubject());
    }

    public Address mapAddressNeo4jToWeb(com.campusconnect.neo4j.types.neo4j.Address address) {
        return new Address(address.getCity(), address.getCountry(), address.getCreatedDate(), address.getId(), address.getLandmark(), address.getLastModifiedTime(), address.getLatitude(),
                address.getLine1(), address.getLine2(), address.getLongitude(), address.getState(), address.getType(), address.getZipCode());
    }

}
