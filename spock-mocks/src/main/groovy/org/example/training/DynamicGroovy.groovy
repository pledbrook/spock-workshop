package org.example.training

/**
 * <p>These exercises are designed to get you working with types that rely on
 * the dynamic behaviour of Groovy. Be sure to read the lecture notes on
 * dynamic and static Groovy. To verify your implementations, simply run the
 * tests in {@code DynamicGroovySpec}.</p>
 */
class DynamicGroovy {

    /**
     * <p>TODO #1: Read the CSV file at the given path and use it to generate
     * a string of the form:</p>
     * <pre>
     *     [title] by [author] (ISBN: [isbn])
     *     [title] by [author] (ISBN: [isbn])
     *     ...
     * </pre>
     * <p>The order of the fields in the CSV is: title, author, ISBN number.
     * <em>Ignore the first row!</em> It contains the field names. Also, make
     * sure that the resulting string ends with a newline, '\n'. You won't have
     * to worry about quotes or escaped commas, so you can easily split each
     * line on the comma itself.</p>
     * <p>Although there are various approaches you can take with this exercise,
     * you should try to create a list of {@code Expando} objects that represent
     * each book, with properties for the title, author and ISBN. Also add a
     * {@code displayName()} method to each {@code Expando} instance that creates
     * the string <tt>"[title] by [author] (ISBN: [isbn])"</tt>. Remember that a
     * method can be added to an {@code Expando} object as a property with the
     * same name as the method and a closure as its value. The parameter list of
     * the closure should match that of the method.</p>
     * <p>Once you have a list of these {@code Expando} objects, use the dynamic
     * {@code displayName()} method to render the final text.</p>
     * <p><b>Variant:</b> Try replacing your use of {@code Expando} with maps
     * instead. You should discover that the code works just the same. That's
     * because Groovy effectively treats maps as if they were dynamic objects,
     * not just as associative arrays. The main difference is that {@code
     * Expando} allows you to override existing methods with dynamically
     * added ones.</p>
     */
    String generateBookDetails(String csvPath) {
        return ""
    }

    /**
     * <p>TODO #2: Read the CSV file at the given path and use it to generate
     * a string of the form:</p>
     * <pre>
     * &lt;books&gt;
     *   &lt;book title='[title]'&gt;
     *     &lt;author&gt;[author]&lt;/author&gt;
     *     &lt;isbn&gt;[isbn]&lt;/isbn&gt;
     *   &lt;/book&gt;
     *   ...
     * &lt;books&gt;
     * </pre>
     * <p>You should use the {@code MarkupBuilder} class for this. Note that it
     * writes directly to {@code stdout} by default, so you should initialise it
     * with a {@code StringWriter} instance. You can then return the string
     * content of that writer at the end of the method.</p>
     * <p>The corresponding test case assumes that you are using the default
     * settings for {@code MarkupBuilder}, but do note that you can configure
     * both the indent and whether to use single or double quotes for attributes.
     * </p>
     */
    String generateXmlBookDetails(String csvPath) {
        return ""
    }

    /**
     * <p>TODO #3:Read the JSON file at the given path and extract the titles of
     * the books from it. The order of the book titles you return should match
     * the order the books appear in the original JSON. You should use the
     * <a href="http://www.groovy-lang.org/json.html">{@code JsonSlurper}</a>
     * class provided by Groovy.</p>
     * <p>Note that {@code JsonSlurper} will return either a JSON object (which
     * you can treat like an {@code Expando} object or map) or a list. Which it
     * returns depends on the JSON. If it begins with a square bracket, you'll
     * get a list. Otherwise it has to start with a curly brace and you'll get
     * a dynamic object instead.</p>
     * <p>Remember the exercises from the <em>groovy2</em> module: those same
     * techniques will be helpful here.</p>
     */
    List<String> getBookTitlesFromJson(String jsonPath) {
        return []
    }
}
