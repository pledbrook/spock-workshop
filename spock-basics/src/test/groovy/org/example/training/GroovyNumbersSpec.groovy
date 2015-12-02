package org.example.training

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * <p>TODO #1: Create a GroovyNumbers class under src/main/groovy in the same
 * package as this test case. You should try to use closure-based methods
 * to implement the methods under test. You will only need to use the ones
 * in the introduction to closures provided as part of the course. The methods
 * you add to {@code GroovyNumbers} may be one-liners.</p>
 * <p>If you're already familiar with the Groovy JDK methods for collections
 * and maps that use closures, try using the
 * <a href="http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html">Java 8 Streams API</a>
 * instead. Closures can be used in place of Java 8 lambda functions without
 * any special syntax, so it's easy. Note that the Java 8 methods are named
 * differently (I'd argue more appropriately) than the corresponding Groovy
 * ones.</p>
 */
class GroovyNumbersSpec extends Specification {
    @Shared numberList = [1, 5, -8, 10, 15, 34, -12]

    /**
     * TODO #2: Create a method in GroovyNumbers that returns a list of the
     * positive numbers (> 0) in {@code numberList}. Don't forget to write
     * the test feature method for it first! Once you have the code working
     * for {@code numberList}, add data sets for an empty list, a list with
     * only negative numbers and a list with only positive numbers.
     */

    /**
     * TODO #3: Create a method in GroovyNumbers that returns a list of the
     * numbers whose magnitude is greater than 10 (numbers have an {@code abs()}
     * method that gives you the magnitude).
     */

    /**
     * TODO #4: Create a method in GroovyNumbers that returns the first number
     * in {@code numberList} that has a magnitude greater than 10.
     */

    /**
     * TODO #5: Create a method in GroovyNumbers that returns a list of the
     * squares (x ** 2) of the numbers in {@code numberList}.
     */

    /**
     * TODO #6: Create a method in GroovyNumbers that calculates the number of
     * elements in {@code numberList} that have a magnitude greater than 10.
     * There's a {@code count()} method in the Groovy JDK that should help.
     */

    /**
     * TODO #7: Create a method in GroovyNumbers that returns a list of the
     * even numbers between 0 and 20 inclusive. Note that the {@code collect()}
     * method can be applied to ranges.
     */
}
