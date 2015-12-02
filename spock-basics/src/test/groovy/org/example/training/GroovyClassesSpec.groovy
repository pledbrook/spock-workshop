package org.example.training

import groovy.transform.Immutable
import spock.lang.Shared
import spock.lang.Specification

/**
 * TODO #21: Create a GroovyClasses class under src/main/groovy in the same
 * package as this test case.
 */
class GroovyClassesSpec extends Specification {
    @Shared people = [
            new Person("Joe", Date.parse("yyyy-MM-dd", "1990-01-02")),
            new Person("Anne", Date.parse("yyyy-MM-dd", "1985-05-27")),
            new Person("Roberto", Date.parse("yyyy-MM-dd", "1960-11-23")),
            new Person("Lizzy", Date.parse("yyyy-MM-dd", "1985-08-12")),
            new Person("Edward", Date.parse("yyyy-MM-dd", "1978-02-28")),
            new Person("Veronica", Date.parse("yyyy-MM-dd", "1964-12-25")) ]


    /**
     * TODO #22: Create a method in GroovyClasses that returns a list of the
     * people's names in {@code people}, sorted by name length. Note that you
     * can use the
     * <a href="http://mrhaki.blogspot.co.uk/2009/08/groovy-goodness-spread-dot-operator.html">spread-dot operator</a>
     * in place of the {@code collect()} method. I do recommend always using
     * {@code *.} in place of a straight {@code .} though. The latter is too
     * confusing.
     */

    /**
     * TODO #23: Create a method in GroovyClasses that returns a list of the
     * dates of birth in {@code people}, sorted by most recent first.
     */

    /**
     * TODO #24: Create a method in GroovyClasses that returns a list of strings
     * of the form <tt>"$name ($age)"</tt>. You'll have to calculate the current
     * age from the date of birth.
     */

}

@Immutable
class Person {
    String name
    Date dob
}
