package org.example.training.mocks

import groovy.json.JsonSlurper

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

        def json = new JsonSlurper().parseText(jsonString)
        return json.books.collect { bookDefn ->
            dao.persist(new Book(bookDefn.title, bookDefn.author))
        }
    }
}
