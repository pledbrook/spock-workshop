package org.example.training

import spock.lang.Specification
import spock.lang.Unroll

/**
 * <p>The {@link DuckTyping} class doesn't declare any types, which means that
 * you can pass any objects into the methods as long as they have the required
 * properties and methods. Make use of {@code Expando} or maps to mimic the
 * objects expected by the methods under test.</p>
 * <p>You can find a useful article on duck typing in Groovy
 * <a href="https://objectpartners.com/2013/08/19/optional-typing-in-groovy/">here</a>
 * .</p>
 */
class DuckTypingSpec extends Specification {

    def "Get the full names of people"() {
        given: "The exercise"
        def exercise = new DuckTyping()

        and: "An initial list of people"
        // TODO #6: create a list of objects (Expando or Map is good here) that
        // satisfy the requirements of the method under test and the test data.
        // Have a look at the method to see what it does with its argument.
        def people

        expect: "A list of the full names of given Person objects"
        exercise.fullNames(people) == ["Joe Bloggs", "Jill Dash", "Arthur Dent", "Selina Kyle"]
    }

    def "Make names all upper case"() {
        given: "The exercise"
        def exercise = new DuckTyping()

        and: "An initial person"
        // TODO #7: create any object that satisfies the requirements of the
        // method under test and the test data.
        def person

        when: "I try to upper cast the names of a given person"
        exercise.namesToUpperCase(person)

        then: "The first and last names are updated appropriately"
        person.firstName == "JOE"
        person.lastName == "BLOGGS"
    }

    @Unroll
    def "Fetch first #count characters of a text file"() {
        given: "The exercise"
        def exercise = new DuckTyping()

        expect: "The correct size of the test file to be returned"
        // TODO #8: create any object that satisfies the requirements of the
        // method under test and the test data. However, the object shouldn't
        // interact with the file system (so that rules out File)!
        def testFilePath
        exercise.firstChars(testFilePath, count) == expected

        where:
        count | expected
        0     | ""
        1     | "L"
        20    | "Lorem ipsum dolor si"
    }
}
