package com.campusconnect.neo4j.tests.functional;

import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.tests.base.DataBrewer;
import com.campusconnect.neo4j.types.web.*;
import com.campusconnect.neo4j.util.Constants;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.campusconnect.neo4j.tests.helper.AssertionHelper.assertBook;
import static com.campusconnect.neo4j.util.Constants.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.testng.Assert.*;

public class BookResourceTest extends TestBase {
    public static final int GONE_GIRL_GR_ID = 11084145;
    public static final long GONE_GIRL_ISBN = 9781451648539L;

    public static final int ALASKA_GR_ID = 99561;


    private Book alaskaBook = getBook(ALASKA_GR_ID);
    private Book goneGirlBook = getBook(GONE_GIRL_GR_ID);
    private String bookId;
    private String dummyBookId1;
    private String dummyBookId2;

    private String userId;
    private String borrowerId;

    @BeforeClass
    public void setUp() {
        //createUser for linking books


        userId = UserResourceFuncTest.createUser();
        borrowerId = UserResourceFuncTest.createUser();
        UserResourceFuncTest.createFriendRelation(userId, borrowerId);
    }

    @AfterClass
    public void removeData() {
        //deleteUsers
        UserResourceFuncTest.deleteUser(userId);
        UserResourceFuncTest.deleteUser(borrowerId);
    }

    public static Book getBook(int goodreadsBookId) {
        ClientResponse geBookClientResponse = resource.path("books").path(String.valueOf(goodreadsBookId)).queryParam("idType", "grId")
                .accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

        assertEquals(geBookClientResponse.getStatus(), 200);
        Book createdBook = geBookClientResponse.getEntity(Book.class);
        assertNotNull(createdBook);
        assertNotNull(createdBook.getId());

        return createdBook;
    }

    public static String createDummyBook() {
        final Book rawCreateBook = DataBrewer.getFakeBook();
        ClientResponse createBookResponse = resource.path("books").type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE).entity(rawCreateBook).post(ClientResponse.class);
        assertEquals(createBookResponse.getStatus(), 201);
        Book createdBook = createBookResponse.getEntity(Book.class);
        assertNotNull(createdBook);
        assertBook(createdBook, rawCreateBook);
        return createdBook.getId();
    }

    @Test
    public void shouldCreateDummyBook() {
        dummyBookId1 = createDummyBook();
    }

    @Test(dependsOnMethods = "shouldCreateDummyBook")
    public void shouldGetBook() {
        ClientResponse getBookResponse = resource.path("books").path(dummyBookId1).type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        assertEquals(getBookResponse.getStatus(), 200);
        Book getBook = getBookResponse.getEntity(Book.class);
        assertNotNull(getBook);
    }

    @Test
    public void shouldGetBookByGoodreadsId() {
        ClientResponse geBookClientResponse = resource.path("books").path(String.valueOf(GONE_GIRL_GR_ID)).queryParam("idType", "grId")
                .accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

        assertEquals(geBookClientResponse.getStatus(), 200);
        Book getBookResult = geBookClientResponse.getEntity(Book.class);
        assertNotNull(getBookResult);
        assertNotNull(getBookResult.getId());

        //Get back the book again n see if the fields match especially Id
        ClientResponse reGeBookClientResponse = resource.path("books").path(String.valueOf(GONE_GIRL_GR_ID)).queryParam("idType", "grId")
                .accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        assertEquals(reGeBookClientResponse.getStatus(), 200);
        alaskaBook = reGeBookClientResponse.getEntity(Book.class);
        assertNotNull(alaskaBook);

        assertBook(alaskaBook, getBookResult);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldGetBookByISBN() {
        ClientResponse geBookClientResponse = resource.path("books/isbn").path(String.valueOf(GONE_GIRL_ISBN))
                .accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        assertEquals(geBookClientResponse.getStatus(), 200);
        Book getBookResult = geBookClientResponse.getEntity(Book.class);
        assertNotNull(getBookResult);
        assertNotNull(getBookResult.getId());

        assertBook(getBookResult, alaskaBook);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsOwnByIdType() {
        ClientResponse addBookAsOwnedToBorrowerClientResponse = resource.path("books").path(goneGirlBook.getId()).path("users").path(borrowerId)
                .queryParam("idType", ID).queryParam("listingType", Constants.OWNS_RELATION).queryParam("status", AVAILABLE).accept(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class);
        assertEquals(addBookAsOwnedToBorrowerClientResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsOwnByGRIdType() {
        ClientResponse addBookAsOwnedClientResponse = resource.path("books").path(alaskaBook.getGoodreadsId().toString()).path("users").path(borrowerId)
                .queryParam("idType", GR_ID).queryParam("listingType", Constants.OWNS_RELATION).queryParam("status", PRIVATE).accept(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class);

        assertEquals(addBookAsOwnedClientResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsWishListByIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(goneGirlBook.getId()).path("users").path(userId)
                .queryParam("idType", ID).queryParam("listingType", Constants.WISHLIST_RELATION)
                .accept(APPLICATION_JSON).post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsWishListByGRIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(alaskaBook.getGoodreadsId().toString()).path("users").path(userId)
                .queryParam("idType", GR_ID).queryParam("listingType", Constants.WISHLIST_RELATION)
                .accept(APPLICATION_JSON).post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsCurrentlyReadingByIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(goneGirlBook.getId()).path("users").path(userId)
                .queryParam("idType", ID).queryParam("listingType", Constants.CURRENTLY_READING_RELATION)
                .accept(APPLICATION_JSON).post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsCurrentlyReadingByGRIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(alaskaBook.getGoodreadsId().toString()).path("users").path(userId)
                .queryParam("idType", GR_ID).queryParam("listingType", Constants.CURRENTLY_READING_RELATION)
                .accept(APPLICATION_JSON).post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsReadingByIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(goneGirlBook.getId()).path("users").path(userId)
                .queryParam("idType", ID).queryParam("listingType", Constants.READ_RELATION).accept(APPLICATION_JSON)
                .post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsReadingByGRIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(alaskaBook.getGoodreadsId().toString()).path("users").path(userId)
                .queryParam("idType", GR_ID).queryParam("listingType", Constants.READ_RELATION).accept(APPLICATION_JSON)
                .post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = {"shouldAddBookAsCurrentlyReadingByIdType", "shouldAddBookAsCurrentlyReadingByGRIdType",
            "shouldAddBookAsOwnByIdType", "shouldAddBookAsOwnByGRIdType",
            "shouldAddBookAsReadingByIdType", "shouldAddBookAsReadingByGRIdType",
            "shouldAddBookAsWishListByIdType", "shouldAddBookAsWishListByGRIdType"}
    )
    public void shouldGetAllBooksOfTheUser() {
        ClientResponse allBooksResponse = resource.path("users").path(userId).path("books")
                .queryParam("filter", Constants.ALL).accept(APPLICATION_JSON).get(ClientResponse.class);
        assertEquals(allBooksResponse.getStatus(), 200);
        AllBooks allBooks = allBooksResponse.getEntity(AllBooks.class);
        // assertEquals(allBooks.getOwnedBooks().size(), 2);
        assertEquals(allBooks.getCurrentlyReadingBooks().size(), 2);
        assertEquals(allBooks.getReadBooks().size(), 2);
        assertEquals(allBooks.getWishlistBooks().size(), 2);
    }

    @Test(dependsOnMethods = {"shouldGetAllBooksOfTheUser"})
    public void shouldGetOwnedBooks() {
        ClientResponse ownsBookResponse = resource.path("users").path(userId).path("books")
                .queryParam("filter", OWNS).accept(APPLICATION_JSON).get(ClientResponse.class);

        assertEquals(ownsBookResponse.getStatus(), 200);
        OwnedBooksPage ownedBooks = ownsBookResponse.getEntity(OwnedBooksPage.class);
        assertEquals(ownedBooks.getOwnedBooks().size(), 2);
        //look which two books present along with status
    }

    @Test(dependsOnMethods = {"shouldGetAllBooksOfTheUser"})
    public void shouldGetAvailableBooks() {
        ClientResponse ownsBookResponse = resource.path("users").path(userId).path("books")
                .queryParam("filter", AVAILABLE).accept(APPLICATION_JSON).get(ClientResponse.class);
        assertEquals(ownsBookResponse.getStatus(), 200);
        OwnedBooksPage ownedBooks = ownsBookResponse.getEntity(OwnedBooksPage.class);
        assertEquals(ownedBooks.getOwnedBooks().size(), 1);
    }

    @Test(dependsOnMethods = "shouldGetAllBooksOfTheUser")
    public void shouldGetWishlistBooks() {
        ClientResponse wishlistBookResponse = resource.path("users").path(userId).path("books")
                .queryParam("filter", WISHLIST).accept(APPLICATION_JSON).get(ClientResponse.class);
        assertEquals(wishlistBookResponse.getStatus(), 200);
        WishListBooksPage ownedBooks = wishlistBookResponse.getEntity(WishListBooksPage.class);
        assertEquals(ownedBooks.getWishListBooks().size(), 2);
    }

    @Test(dependsOnMethods = "shouldGetAllBooksOfTheUser")
    public void shouldGetReadBooks() {
        ClientResponse wishlistBookResponse = resource.path("users").path(userId).path("books")
                .queryParam("filter", READ).accept(APPLICATION_JSON).get(ClientResponse.class);
        assertEquals(wishlistBookResponse.getStatus(), 200);
        BooksPage ownedBooks = wishlistBookResponse.getEntity(BooksPage.class);
        assertEquals(ownedBooks.getBooks().size(), 2);
    }

    @Test(dependsOnMethods = "shouldGetAllBooksOfTheUser")
    public void shouldGetCurrentlyReadingBooks() {
        ClientResponse wishlistBookResponse = resource.path("users").path(userId).path("books")
                .queryParam("filter", CURRENTLY_READING).accept(APPLICATION_JSON).get(ClientResponse.class);
        assertEquals(wishlistBookResponse.getStatus(), 200);
        CurrentlyReadingBooksPage currentlyReadingBooks = wishlistBookResponse.getEntity(CurrentlyReadingBooksPage.class);
        assertEquals(currentlyReadingBooks.getCurrentlyReadingBooks().size(), 2);
    }

    @Test(dependsOnMethods = "shouldGetAllBooksOfTheUser")
    public void shouldSearchAndFilterUserBooks() {
        ClientResponse searchBookResponse = resource.path("books").path("search")
                .queryParam(SEARCH_QPARAM, alaskaBook.getName()).accept(APPLICATION_JSON).get(ClientResponse.class);
        assertEquals(searchBookResponse.getStatus(), 200);
        SearchResult searchResult = searchBookResponse.getEntity(SearchResult.class);
        assertTrue(searchResult.getBooks().size() > 0);
        assertTrue(checkIfSearchResultGotGoodReadsBook(alaskaBook, searchResult.getBooks()));
        //assertForReadStatus();
    }

    private boolean checkIfSearchResultGotGoodReadsBook(Book goodReadsBook, List<Book> books) {
        for (Book book : books) {
            if (goodReadsBook.getGoodreadsId().equals(book.getGoodreadsId()))
                return true;
        }
        return false;
    }

    private Book assertBookWhichIsListedUnderUser(Book goodReadsBook, List<Book> books) {
        for (Book book : books) {
            if (goodReadsBook.getGoodreadsId().equals(book.getGoodreadsId()))
                return book;
        }
        return null;
    }
}
