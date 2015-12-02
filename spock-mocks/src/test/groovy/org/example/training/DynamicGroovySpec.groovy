package org.example.training

import spock.lang.Specification

class DynamicGroovySpec extends Specification {
    final String testCsvPath = resolveFilePath("groovy3/src/test/resources/books.csv")
    final String testJsonPath = resolveFilePath("groovy3/src/test/resources/books.json")

    def "Should generate correct output from a CSV file"() {
        given: "The exercise"
        def exercise = new DynamicGroovy()

        expect: "A string listing the details of each book in the CSV file"
        exercise.generateBookDetails(testCsvPath) == """\
Colossus by Niall Ferguson (ISBN: 32486286)
Empire by Niall Ferguson (ISBN: 29457346)
Misery by Stephen King (ISBN: 04353487)
The Kite Runner by Khaled Hosseini (ISBN: 43527654)
The Chamber of Secrets by J. K. Rowling (ISBN: 14358761)
"""
    }

    def "Should generate correct XML output from a CSV file"() {
        given: "The exercise"
        def exercise = new DynamicGroovy()

        expect: "An XML string containing all the book details"
        exercise.generateXmlBookDetails(testCsvPath).trim() == """\
<books>
  <book title='Colossus'>
    <author>Niall Ferguson</author>
    <isbn>32486286</isbn>
  </book>
  <book title='Empire'>
    <author>Niall Ferguson</author>
    <isbn>29457346</isbn>
  </book>
  <book title='Misery'>
    <author>Stephen King</author>
    <isbn>04353487</isbn>
  </book>
  <book title='The Kite Runner'>
    <author>Khaled Hosseini</author>
    <isbn>43527654</isbn>
  </book>
  <book title='The Chamber of Secrets'>
    <author>J. K. Rowling</author>
    <isbn>14358761</isbn>
  </book>
</books>"""
    }

    def "Should extract book titles from a JSON file"() {
        given: "The exercise"
        def exercise = new DynamicGroovy()

        expect: "A list of the full names of given Person objects"
        exercise.getBookTitlesFromJson(testJsonPath) == ["Colossus", "Empire", "Misery",
                                                 "The Kite Runner", "The Chamber of Secrets"]
    }

    private String resolveFilePath(String path) {
        def parentDir = System.getProperty("project.root.dir")
        if (parentDir) return new File(parentDir, path).path
        else return path
    }
}
