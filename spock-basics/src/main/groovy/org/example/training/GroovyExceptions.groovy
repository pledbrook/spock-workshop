package org.example.training

/**
 * <p>These exercises give you a chance to work with exception-based error
 * handling. If you're not familiar with exceptions, you should follow the
 * official
 * <a href="https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html">Java
 * tutorial</a> to learn more.</p>
 * <p><b>Note</b> Although Groovy's exception support is largely the same as
 * Java's, a key difference is that Groovy does <em>not</em> force you to catch
 * checked exceptions. As far as Groovy is concerned, checked exceptions are
 * the same as runtime exceptions.</p>
 */
class GroovyExceptions {

    /**
     * <p>TODO #26: Calculate the length of a right-angled triangle using the
     * Pythagorean Theorem. This time, check that the arguments both have
     * values greater than zero. If either of them is zero or negative, then
     * throw an {@code IllegalArgumentException} with the message "Sides must
     * have a length greater than zero". See the Groovy syntax quick reference
     * to find out how to throw an exception.</p>
     */
    double hypotenuseLength(double side1, double side2) {
        return -1
    }

    /**
     * <p>TODO #27: Calculate the number of characters in a file, handling different
     * types of error:</p>
     * <ul>
     * <li>If the given path is null or empty, throw an {@code IllegalArgumentException}
     * with the message "Path is null or empty: '${&lt;valueOfPath&gt;}'"</li>
     * <li>If the path doesn't represent a file that exists, return -1</li>
     * </ul>
     * <p>In the second case, you should ideally catch the {@code FileNotFoundException}
     * that's thrown by the <tt>getText()</tt> method, just to practice exception
     * handling. In the real world, you would normally check for the existence of the
     * file first (unless you're sure it's supposed to be there).</p>
     */
    long characterCount(String path) {
        return -1
    }
}
