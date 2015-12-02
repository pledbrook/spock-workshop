package org.example.training

import spock.lang.Shared
import spock.lang.Specification

/**
 * <p>TODO #15: Create a GroovyInterfaces class under src/main/groovy in the same
 * package as this test case. Also create two other classes (either in the same
 * file or use the standard class per file approach):</p>
 * <ul>
 *   <li>StringLengthComparator</li>
 *   <li>CaseInsensitiveComparator</li>
 * </ul>
 * <p>These classes should implement the {@code java.util.Comparator} interface
 * (allow the IDE to automatically generate the required methods for you). Implement
 * the {@code compareTo()} method in each of the classes based on their names. So
 * the {@code StringLengthComparator} implementation should compare the lengths
 * of the two string arguments, while {@code CaseInsensitiveComparator} should do
 * an case-insensitive alphabetic sort.</p>
 * <p>If you're not familiar with interfaces, they are an example of abstract
 * types: they define behaviour through a declared set of methods and their
 * signatures, but don't provide an implementation. For example, {@code List}
 * is a type representing lists. It defines how you can add elements to a list,
 * get the element at a particular index, and get the element count (among
 * other things). But it does not provide an implementation of that contract.
 * That's what classes do, such as {@code ArrayList}, which uses an array to
 * store the list elements. This is what polymorphism means: by interacting
 * with objects via an abstract type ({@code List}), your code can work with
 * many different implementations, including mock ones.</p>
 * <p>There are several rules regarding interfaces and classes:
 * <ul>
 *   <li>A class can <em>extend</em> at most one other class (inheritance)</li>
 *   <li>A class can <em>implement</em> one or more interfaces</li>
 *   <li>An interface can <em>extend</em> one or more other interaces</li>
 *   <li>A class is abstract if it has any declared, but unimplemented
 *   methods</li>
 *   <li>You cannot instantiate an abstract type (interfaces and abstract
 *   classes</li>
 * </ul>
 * <p>There is much more to object oriented design, but the above should be
 * sufficient for the course.</p>
 */
class GroovyInterfacesSpec extends Specification {
    @Shared strings = [
            "oranges",
            "Lemons",
            "pears",
            "Figs",
            "pineapples" ]

    /**
     * TODO #16: Create a method in GroovyInterfaces that returns a list of the
     * words in {@code strings} sorted by word length. In the implementation,
     * use the {@code sort(boolean, Comparator)} method added to lists by the
     * Groovy JDK and pass in the {@code Comparator} implementation you created
     * that matches the required behaviour.
     */

    /**
     * TODO #17: Create a method in GroovyInterfaces that returns a list of the
     * words in {@code strings} sorted alphabetically, ignoring case. In the
     * implementation, use the {@code sort(boolean, Comparator)} method as in
     * the previous exercise.
     */
}
