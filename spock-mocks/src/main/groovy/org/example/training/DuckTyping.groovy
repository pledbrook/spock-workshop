package org.example.training

/**
 *
 */
class DuckTyping {


    def fullNames(people) {
        def result = []
        for (p in people) {
            result << p.fullName
        }
        return result
    }

    void namesToUpperCase(person) {
        person.firstName = person.firstName.toUpperCase()
        person.lastName = person.lastName.toUpperCase()
    }

    String firstChars(path, int count) {
        def buffer = new char[count]
        path.withReader("UTF-8") { reader ->
            reader.read(buffer, 0, count)
        }
        return buffer.toString()
    }
}
