package org.example.training

import org.junit.Assert
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * These are the first set of exercises for the Spock workshop. All you need
 * to do is write some feature methods to verify the behaviour of the methods
 * in the {@link Exercises} class. No mocking is required, but you will need
 * to handle exceptions and multiple data sets.
 */
class Workshop01Spec extends Specification {
    final String testFilePath = resolveFilePath("spock-basics/src/test/resources/README.txt")
    final exercises = new Exercises()

    @Shared def samplePeople = [
            new Person(firstName: "Joe", lastName: "Bloggs"),
            new Person(firstName: "Jill", lastName: "Dash"),
            new Person(firstName: "Arthur", lastName: "Dent"),
            new Person(firstName: "Selina", lastName: "Kyle") ]

    /**
     * <p>TODO #01: Write a feature method for the {@link Exercises#hypotenuseLength(double, double)}
     * method. Think of suitable data sets, such as what happens if one or both
     * sides are zero.</p>
     */
    @Unroll
    def "Calculate hypotenuse for sides #sideA & #sideB"() {
        expect: "Hypotenuse length is calculated correctly"
        Assert.assertEquals(exercises.hypotenuseLength(sideA, sideB), expected, 0.001)

        where:
        sideA | sideB | expected
        3     | 4     | 5.0D
        4     | 2     | 4.472D
        1.23  | 2.67  | 2.940D
        0     | 6     | 6.0D
        0     | 0     | 0.0D
    }

    /**
     * <p>TODO #02: Write a feature method for {@link Exercises#hypotenuseLength(double, double)}
     * that checks the behaviour when either argument is negative. The method
     * should throw an IllegalArgumentException in this case.</p>
     */
    @Unroll
    def "Handle invalid arguments when calculating hypotenuse for sides #sideA & #sideB"() {
        when: "I calculate the hypoteneuse with invalid side lengths"
        exercises.hypotenuseLength(sideA, sideB)

        then: "An IllegalArgumentException is thrown with the appropriate message"
        def ex = thrown(IllegalArgumentException)
        ex.message == "`${negSide}` cannot be negative"

        where:
        sideA | sideB
        -1.23 | -2.67
        4     | -3

        negSide = sideA < 0 ? "side1" : "side2"
    }

    /**
     * <p>TODO #03: Write a feature method for {@link Exercises#median(java.util.Collection)}.
     * Be sure to check the behaviour for both odd and even numbers of elements
     * as well as an empty collection.</p>
     */
    @Unroll
    def "Calculate median value of #nums"() {
        expect: "The median of a collection of numbers is calculated correctly"
        exercises.median(nums) == expected

        where:
        nums             | expected
        [1]              | 1
        [1, 0, -1]       | 0
        [2, 4, 9, -2, 0] | 2
        [-1, -3, -6, -2] | -2.5
        [1, 1, 3, 3]     | 2
    }

    /**
     * <p>TODO #04: Write a feature method for {@link Exercises#fullNames(java.util.List)}.
     * You can use the {@code samplePeople} property as a source of test data.</p>
     */
    def "Get the full names of people"() {
        expect: "A list of the full names of given Person objects"
        exercises.fullNames(people) == expected

        where:
        people       | expected
        []           | []
        samplePeople | ["Joe Bloggs", "Jill Dash", "Arthur Dent", "Selina Kyle"]
    }

    /**
     * <p>TODO #05: Write a feature method for {@link Exercises#createPeople(java.util.List)}.
     * This is a variation of the previous test, but the result is a collection
     * of {@link Person} objects. Note that {@code Person} is annotated with
     * {@code @Canonical}, they can be easily compared with {@code equals()}.</p>
     */
    def "Create a list of people from full names"() {
        given: "An initial list of names"
        def names = ["Joe Bloggs", "Jill Dash", "Arthur Dent", "Selina Kyle"]

        when: "I create a list of people from those names"
        def people = exercises.createPeople(names)

        then: "I get a list of Person objects with the correct names"
        people.every { it instanceof Person }
        people.size() == names.size()
        people.find { it.firstName == "Joe" && it.lastName == "Bloggs" }
        people.find { it.firstName == "Selina" && it.lastName == "Kyle" }
    }

    /**
     * <p>TODO #06: (Optional) Write a feature method for {@link Exercises#characterCount(java.lang.String)}.
     * The target method creates a new instance of {@code File}, so you will
     * either have to use the files in the test <em>resources</em> directory
     * or mock the creation of the {@code File} somehow.</p>
     */
    def "Calculate the number of characters in a text file"() {
        expect: "The correct size of the test file to be returned"
        exercises.characterCount(testFilePath) == 6874
    }

    private String resolveFilePath(String path) {
        def parentDir = System.getProperty("project.root.dir")
        if (parentDir) return new File(parentDir, path).path
        else return path
    }
}
