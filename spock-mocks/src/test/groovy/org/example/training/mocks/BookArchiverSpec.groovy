package org.example.training.mocks

import spock.lang.Shared
import spock.lang.Specification

class BookArchiverSpec extends Specification {
    @Shared expectedBooks = [
            new Book("Colossus", "Niall Ferguson"),
            new Book("Empire", "Niall Ferguson"),
            new Book("Misery", "Stephen King"),
            new Book("The Kite Runner", "Khaled Hosseini")]

    /**
     * <p>TODO #9: Write a test for the {@link BookArchiver#archiveBooks()} method,
     * which you should also implement. As you'll see from the {@code BookArchiver}
     * class, it depends on two collaborators: a {@link RestClient} and a
     * {@link BookDao}. You will have to provide mock implementations for these as
     * one connects to the internet and the other connects to a database. Unit
     * tests should run without interacting with anything outside of the JVM.</p>
     * <p>Your mock REST client should return a JSON string of the form:
     * <pre>
     *     {"books": [
     *       { "title": "Colossus", "author": "Niall Ferguson"},
     *       { "title": "Empire", "author": "Niall Ferguson"},
     *       { "title": "Misery", "author": "Stephen King"},
     *       { "title": "The Kite Runner", "author": "Khaled Hosseini"}
     *     ]}
     * </pre>
     * which your {@code archiveBooks()} implementation should parse to create
     * {@link Book} instances. Note that {@code Book} is an immutable class so
     * you will need to use a tuple constructor {@code Book(String, String)}
     * rather than the Groovy Beans style.</p>
     * <p>The mock DAO object doesn't need to do anything other than verify
     * that the {@code persist()} method is called for each of the books in the
     * original JSON. It should also return the {@code Book} instance each time.
     * </p>
     * <p>You can use any mocking library you want for this exercise, but I
     * recommend using the built-in Spock support. There a lots of resources
     * online for this, but I list a few here as a shortcut for you:
     * <ul>
     *   <li><a href="https://spockframework.github.io/spock/docs/1.0/interaction_based_testing.html">Spock user guide</a></li>
     *   <li><a href="https://objectpartners.com/2014/04/08/spock-mock-cheatsheet/">Spock Mock cheat sheet</a></li>
     *   <li><a href="www.youtube.com/watch?v=LvWbhRM8Wdg">JetBrainsTV Screencast</a></li>
     * </ul>
     * They should provide enough to get you going, but don't be shy about
     * googling for more guides.</p>
     * <p>Note that {@code RestClient} is a class, and so you need an extra
     * library in order to mock it. Interfaces can be mocked without any extra
     * dependencies at all. The extra dependency has already been added to the
     * Gradle build file (it's <i>cglib:cglib-nodep:2.2</i> if you're interested)
     * so all you need to do is perform a Gradle refresh within IntelliJ.</p>
     * <p>The last thing I want to mention is that the {@code BookArchiver}
     * class requires you to provide the REST client and DAO at instantiation
     * time. This is an example of <i>dependency injection</i> where the user
     * provides the collaborators rather than the class creating them itself.
     * As you can see, this makes testing a lot easier. It's an import technique
     * that's used heavily in Grails.</p>
     */
    def "Should archive all the books provided by the corresponding URL"() {

    }
}
