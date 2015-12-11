package org.example.training

import org.example.training.mocks.Book
import spock.lang.Shared
import spock.lang.Specification

class Workshop03Spec extends Specification {
    @Shared expectedBooks = [
            new Book("Colossus", "Niall Ferguson"),
            new Book("Empire", "Niall Ferguson"),
            new Book("Misery", "Stephen King"),
            new Book("The Kite Runner", "Khaled Hosseini")]

    /**
     * <p>TODO #9: Write a test for the {@link org.example.training.mocks.BookArchiver#archiveBooks()} method .
     * As you'll see from the {@code BookArchiver} class, it depends on two
     * collaborators: a {@link org.example.training.mocks.RestClient} and a
     * {@link org.example.training.mocks.BookDao}. You will have to provide
     * mock implementations for these as one connects to the internet and the
     * other connects to a database.</p>
     * <p>Your mock REST client should return a JSON string of the form:
     * <pre>
     *     {"books": [
     *       { "title": "Colossus", "author": "Niall Ferguson"},
     *       { "title": "Empire", "author": "Niall Ferguson"},
     *       { "title": "Misery", "author": "Stephen King"},
     *       { "title": "The Kite Runner", "author": "Khaled Hosseini"}
     *     ]}
     * </pre>
     * which the {@code archiveBooks()} method parses to create {@link Book}
     * instances. You can pull this data from the books.json file if you want.</p>
     * <p>The mock DAO object doesn't need to do anything other than verify
     * that the {@code persist()} method is called for each of the books in the
     * original JSON. It should also return the {@code Book} instance each time.
     * </p>
     */
}
