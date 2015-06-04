package com.campusconnect.neo4j.tests.functional.base;

import com.campusconnect.neo4j.types.common.AddressType;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.web.BorrowRequest;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by sn1 on 1/19/15.
 */
public class DataBrewer {
    public static final Faker faker = new Faker();

    public static User getFakeUser() {
        User user = new User(faker.name().name(), faker.internet().emailAddress());
        user.setProfileImageUrl("https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg");
        return user;
    }

    public static Book getFakeBook() {
        return new Book(faker.name().name(), UUID.randomUUID().toString());
    }

    public static Group getFakeGroup() {
        return new Group(faker.name().name());
    }

    public static User getFakeUserWithAddress() {
        User user = new User(faker.name().name(), faker.internet().emailAddress(), faker.phoneNumber().phoneNumber());
        Set<Address> addresses = new HashSet<>();
        addresses.add(getFakeAddress(AddressType.HOME.toString()));
        user.setAddresses(addresses);
        user.setProfileImageUrl("https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg");
        return user;
    }

    public static User getFakeUserWithKnownEmailAddressAndGoogleId(String userName, String email, String googleId) {
        User user = new User(userName, email, faker.phoneNumber().phoneNumber());
        Set<Address> addresses = new HashSet<>();
        addresses.add(getFakeAddress(AddressType.HOME.toString()));
        user.setAddresses(addresses);
        user.setGoogleId(googleId);
        user.setProfileImageUrl("https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg");
        return user;
    }

    public static User getFakeUserWithKnownEmailAddressAndFbId(String userName, String email, String fbId) {
        User user = new User(userName, email, faker.phoneNumber().phoneNumber());
        Set<Address> addresses = new HashSet<>();
        addresses.add(getFakeAddress(AddressType.HOME.toString()));
        user.setAddresses(addresses);
        user.setFbId(fbId);
        user.setProfileImageUrl("https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg");
        return user;
    }

    public static User getUserWithTwoAddresses() {
        User user = getFakeUser();
        Set<Address> addresses = new HashSet<>();
        addresses.add(getFakeAddress(AddressType.HOME.toString()));
        addresses.add(getFakeAddress(AddressType.WORK.toString()));
        user.setAddresses(addresses);
        return user;
    }

    public static Address getFakeAddress(String addressType) {
        Address address = new Address();
        address.setLine1(faker.address().streetAddressNumber());
        address.setLine2(faker.address().secondaryAddress());
        address.setType(addressType);
        address.setLandmark("landMark");
        address.setCity(faker.address().cityPrefix() + " " + faker.address().citySuffix());
        address.setState(faker.address().stateAbbr());
        address.setCountry(faker.address().country());
        address.setZipCode(faker.address().zipCode());
        return address;
    }

    public static BorrowRequest getBorrowRequest(String ownerId, String borrwerId) {
        return new BorrowRequest(ownerId, borrwerId, 25, System.currentTimeMillis(), "message");
    }

    public static Reminder getFakeReminder(String subject) {

        Long currentTime = System.currentTimeMillis();
        return new Reminder(currentTime, currentTime, subject, faker.lorem().fixedString(20), "desc", currentTime);
    }

    public static Favourite getFakeFavourites(String genre) {

        return new Favourite(genre);
    }

}
