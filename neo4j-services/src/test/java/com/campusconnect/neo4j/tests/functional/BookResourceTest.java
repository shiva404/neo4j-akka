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

import static com.campusconnect.neo4j.tests.helper.AssertionHelper.assertBook;
import static com.campusconnect.neo4j.util.Constants.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BookResourceTest extends TestBase {
    public static final int GONE_GIRL_GR_ID = 11084145;
    public static final long GONE_GIRL_ISBN = 9781451648539L;
    private final Book rawCreateBook = DataBrewer.getFakeBook();
    private Book goodReadsBook;
    private String bookId;
    private Book createdBook;
    private String userId;
    private String borrowerId;


    @BeforeClass
    public void setUp() {
        //createUser for linking books
        userId = UserResourceTest.createUser();
        borrowerId = UserResourceTest.createUser();
    }

    @AfterClass
    public void removeData() {
        //deleteUsers
        UserResourceTest.deleteUser(userId);
        UserResourceTest.deleteUser(borrowerId);
    }


    @Test
    public void shouldCreateBook() {
        ClientResponse createBookResponse = resource.path("books").type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE).entity(rawCreateBook).post(ClientResponse.class);
        assertEquals(createBookResponse.getStatus(), 201);
        createdBook = createBookResponse.getEntity(Book.class);
        assertNotNull(createdBook);
        assertBook(createdBook, rawCreateBook);
        bookId = createdBook.getId();
    }

    @Test(dependsOnMethods = "shouldCreateBook")
    public void shouldGetBookById() {
        ClientResponse geBookClientResponse = resource.path("books").path(bookId).queryParam("idType", "id")
                .accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        assertEquals(geBookClientResponse.getStatus(), 200);
        Book getBookResult = geBookClientResponse.getEntity(Book.class);
        assertNotNull(getBookResult);
        assertBook(getBookResult, createdBook);
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
        goodReadsBook = reGeBookClientResponse.getEntity(Book.class);
        assertNotNull(goodReadsBook);

        assertBook(goodReadsBook, getBookResult);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldGetBookByISBN() {
        ClientResponse geBookClientResponse = resource.path("books/isbn").path(String.valueOf(GONE_GIRL_ISBN))
                .accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        assertEquals(geBookClientResponse.getStatus(), 200);
        Book getBookResult = geBookClientResponse.getEntity(Book.class);
        assertNotNull(getBookResult);
        assertNotNull(getBookResult.getId());

        assertBook(getBookResult, goodReadsBook);
    }

    @Test(dependsOnMethods = "shouldCreateBook")
    public void shouldAddBookAsOwnByIdType() {
        ClientResponse addBookAsOwnedClientResponse = resource.path("books").path(createdBook.getId()).path("users").path(userId)
                .queryParam("idType", ID).queryParam("listingType", Constants.OWNS_RELATION).queryParam("status", AVAILABLE).accept(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class);
        assertEquals(addBookAsOwnedClientResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldGetBookByGoodreadsId")
    public void shouldAddBookAsOwnByGRIdType() {
        ClientResponse addBookAsOwnedClientResponse = resource.path("books").path(goodReadsBook.getGoodreadsId().toString()).path("users").path(userId)
                .queryParam("idType", GR_ID).queryParam("listingType", Constants.OWNS_RELATION).queryParam("status", PRIVATE).accept(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class);

        assertEquals(addBookAsOwnedClientResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldCreateBook")
    public void shouldAddBookAsWishListByIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(createdBook.getId()).path("users").path(userId)
                .queryParam("idType", ID).queryParam("listingType", Constants.WISHLIST_RELATION)
                .accept(APPLICATION_JSON).post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldCreateBook")
    public void shouldAddBookAsWishListByGRIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(goodReadsBook.getGoodreadsId().toString()).path("users").path(userId)
                .queryParam("idType", GR_ID).queryParam("listingType", Constants.WISHLIST_RELATION)
                .accept(APPLICATION_JSON).post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldCreateBook")
    public void shouldAddBookAsCurrentlyReadingByIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(createdBook.getId()).path("users").path(userId)
                .queryParam("idType", ID).queryParam("listingType", Constants.CURRENTLY_READING_RELATION)
                .accept(APPLICATION_JSON).post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldCreateBook")
    public void shouldAddBookAsCurrentlyReadingByGRIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(goodReadsBook.getGoodreadsId().toString()).path("users").path(userId)
                .queryParam("idType", GR_ID).queryParam("listingType", Constants.CURRENTLY_READING_RELATION)
                .accept(APPLICATION_JSON).post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldCreateBook")
    public void shouldAddBookAsReadingByIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(createdBook.getId()).path("users").path(userId)
                .queryParam("idType", ID).queryParam("listingType", Constants.READ_RELATION).accept(APPLICATION_JSON)
                .post(ClientResponse.class);
        assertEquals(addBookAsWishListResponse.getStatus(), 200);
    }

    @Test(dependsOnMethods = "shouldCreateBook")
    public void shouldAddBookAsReadingByGRIdType() {
        ClientResponse addBookAsWishListResponse = resource.path("books").path(goodReadsBook.getGoodreadsId().toString()).path("users").path(userId)
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
        assertEquals(allBooks.getOwnedBooks().size(), 2);
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
    void shouldGetCurrentlyReadingBooks() {
        ClientResponse wishlistBookResponse = resource.path("users").path(userId).path("books")
                .queryParam("filter", CURRENTLY_READING).accept(APPLICATION_JSON).get(ClientResponse.class);
        assertEquals(wishlistBookResponse.getStatus(), 200);
        CurrentlyReadingBooksPage currentlyReadingBooks = wishlistBookResponse.getEntity(CurrentlyReadingBooksPage.class);
        assertEquals(currentlyReadingBooks.getCurrentlyReadingBooks().size(), 2);
    }
}
