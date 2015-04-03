package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.tests.functional.base.DataBrewer;
import com.campusconnect.neo4j.types.Book;
import org.springframework.beans.factory.annotation.Autowired;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * Created by I308260 on 2/17/2015.
 */
public class BookTest extends TestBase {
    @Autowired
    BookDao bookDao;

    Book createdBook ;

    @Test
    public void createBook(){
        createdBook = bookDao.createBook(DataBrewer.getFakeBook());
       // assert book.getId() != null;
        assertNotNull(createdBook.getId(),"no id created when a new book is created");
        assertNotNull(createdBook.getNodeId(),"no node id created when a new book is created");

    }

    @Test(dependsOnMethods = "createBook")
    public void getBook()
    {
        Book book = bookDao.getBook(createdBook.getId());
        assertEquals(createdBook.getIsbn(),book.getIsbn(),"created book retrieved book isbn do not match");
        assertEquals(createdBook.getName(),book.getName(),"created book retrieved book names do not match");
        assertEquals(createdBook.getId(),book.getId(),"created book retrieved book id do not match");
        assertEquals(createdBook.getNodeId(),book.getNodeId(),"created book retrieved book node id do not match");

    }




}
