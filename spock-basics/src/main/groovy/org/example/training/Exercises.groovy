package org.example.training

/**
 *
 */
class Exercises {

    double hypotenuseLength(double side1, double side2) {
        if (side1 < 0) throw new IllegalArgumentException("`side1` cannot be negative")
        if (side2 < 0) throw new IllegalArgumentException("`side2` cannot be negative")
        return Math.sqrt(side1 ** 2 + side2 ** 2)
    }

    BigDecimal median(Collection<Integer> numbers) {
        if (numbers.size() % 2 == 0) {
            // Even number of values
            def firstIndex = numbers.size().intdiv(2) - 1
            return mean(numbers.sort()[firstIndex..(firstIndex + 1)])
        } else {
            def index = numbers.size().intdiv(2)
            return numbers.sort()[index]
        }
    }

    BigDecimal mean(Collection<Integer> numbers) {
        return numbers.sum() / numbers.size()
    }

    List<String> fullNames(List<Person> people) {
        return people*.fullName
    }

    List<Person> createPeople(List<String> names) {
        return names.collect {
            def (first, last) = it.split(/\s+/)
            new Person(firstName: first, lastName: last)
        }
    }

    long characterCount(String path) {
        return new File(path).getText("UTF-8").size()
    }
}
