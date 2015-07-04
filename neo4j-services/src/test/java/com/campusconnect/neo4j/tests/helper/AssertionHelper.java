package com.campusconnect.neo4j.tests.helper;

import com.campusconnect.neo4j.types.web.Book;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/26/15
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssertionHelper {

    public static void assertBook(Book resultBook, Book expectedBook) {
        //Assert for sys generated fields
        assertNotNull(resultBook.getId());

        if (expectedBook.getId() != null)
            assertEquals(resultBook.getId(), expectedBook.getId());

        assertEquals(resultBook.getAuthorName(), expectedBook.getAuthorName());
        assertEquals(resultBook.getDescription(), expectedBook.getDescription());
        assertEquals(resultBook.getImageUrl(), expectedBook.getImageUrl());
        assertEquals(resultBook.getIsbn(), expectedBook.getIsbn());
        assertEquals(resultBook.getIsbn13(), expectedBook.getIsbn13());
        assertEquals(resultBook.getName(), expectedBook.getName());
        assertEquals(resultBook.getNumberOfPages(), expectedBook.getNumberOfPages());
        assertEquals(resultBook.getPublishedYear(), expectedBook.getPublishedYear());
        assertEquals(resultBook.getPublisher(), expectedBook.getPublisher());
    }
}
