package com.campusconnect.neo4j.mappers;


import com.campusconnect.neo4j.types.neo4j.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/4/15
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebToNeo4jMapper {

    public static User mapUserWebToNeo4j(com.campusconnect.neo4j.types.web.User user) {
        if (user == null)
            return null;
        Set<Address> addresses = new HashSet<>();
        if (user.getAddresses() != null && user.getAddresses().size() > 0) {
            for (com.campusconnect.neo4j.types.web.Address address : user.getAddresses()) {
                addresses.add(WebToNeo4jMapper.mapAddressWebToNeo4j(address));
            }
        }
        return new User(addresses, user.getCreatedDate(), user.getEmail(), user.getFavorites(), user.getFbId(), user.getGoodreadsAccessToken(), user.getGoodreadsAccessTokenSecret(), user.getGoodreadsAuthStatus(),
                user.getGoodReadsSynchStatus(), user.getLastGoodreadsSychDate(), user.getGoodreadsId(), user.getGoogleId(), user.getId(), user.getLastModifiedDate(), user.getName(), user.getPhone(), user.getGender(),
                user.getProfileImageUrl(), user.getWorkDesignation(), user.getWorkLocation(), user.getUserRelation());
    }

    public static Book mapBookWebToNeo4j(com.campusconnect.neo4j.types.web.Book book) {
        if (book == null)
            return null;
        return new Book(book.getId(), book.getGoodreadsId(), book.getAuthorName(), book.getGoodreadsAuthorId(), book.getName(), book.getIsbn(), book.getIsbn13(),
                book.getPublishedYear(), book.getDescription(), book.getPublisher(), book.getNumberOfPages(), book.getImageUrl());
    }

    public static Favourite mapFavouriteWebToNeo4j(com.campusconnect.neo4j.types.web.Favourite favourite) {
        if (favourite == null)
            return null;
        return new Favourite(favourite.getGenre(), favourite.getNodeId());
    }

    public static Group mapGroupWebToNeo4j(com.campusconnect.neo4j.types.web.Group group) {
        if (group == null)
            return null;
        return new Group(group.getId(), group.getName(), group.getCreatedDate(), group.getLastModifiedTime(), group.getLastModifiedBy(),group.getisPublic());
    }

    public static Reminder mapReminderWebToNeo4j(com.campusconnect.neo4j.types.web.Reminder reminder) {
        if (reminder == null)
            return null;
        return new Reminder(reminder.getCreatedDate(), reminder.getDescription(), reminder.getLastModifiedTime(), reminder.getNodeId(), reminder.getReminderMessage(), reminder.getReminderTime(), reminder.getSubject());
    }

    public static Address mapAddressWebToNeo4j(com.campusconnect.neo4j.types.web.Address address) {
        if (address == null)
            return null;
        return new Address(address.getCity(), address.getCountry(), address.getCreatedDate(), address.getId(), address.getLandmark(), address.getLastModifiedTime(), address.getLatitude(),
                address.getLine1(), address.getLine2(), address.getLongitude(), address.getState(), address.getType(), address.getZipCode());
    }
}
