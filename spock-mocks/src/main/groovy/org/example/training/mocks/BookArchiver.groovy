package org.example.training.mocks

/**
 * Created by pledbrook on 12/10/15.
 */
class BookArchiver {
    static final String BOOKS_URL = "http://www.example.org/books.json"

    final RestClient restClient
    final BookDao dao

    BookArchiver(RestClient client, BookDao dao) {
        this.restClient = client
        this.dao = dao
    }

    List<Book> archiveBooks() {
        def jsonString = restClient.getContent(BOOKS_URL)

        // TODO: Complete the rest of this implementation. See the test case to
        // learn what structure the JSON has and what format the dates take.
        // Note that Book is an immutable class, so you can only use the tuple
        // constructor Book(String, String), not the Groovy Beans one.
    }
}
