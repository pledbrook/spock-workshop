package org.example.training

import spock.lang.Specification

class Workshop04Spec extends Specification {
    static final String SAMPLE_TEXT = """\
Title,Author,ISBN
Colossus,Niall Ferguson,32486286
Empire,Niall Ferguson,29457346
Misery,Stephen King,04353487
The Kite Runner,Khaled Hosseini,43527654
The Chamber of Secrets,J. K. Rowling,14358761
"""

    /**
     * <p>TODO #10: Write a feature method for {@link WordStats#getCharCount()}.
     * You can also add ones for the other methods in that class if you want.
     * Note that the {@code WordStats} class can be initialised with an untyped
     * {@code url} parameter. This is useful, because otherwise the class will
     * load the <em>README.txt</em> file from the classpath. The question is,
     * what approaches can you use to provide the fake url object? Can you use
     * Spock mocks?</p>
     */
    def "Should evaluate the word count for the text at a source URL"() {
        given: "A WordStats instance"
        def stats = new WordStats(GroovyMock(URL) {
            getText("UTF-8") >> SAMPLE_TEXT
        })

        expect: "The character count to match the real value for the sample text"
        stats.charCount == 198
    }
}
