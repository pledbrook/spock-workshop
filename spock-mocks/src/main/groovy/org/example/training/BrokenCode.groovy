package org.example.training

import groovy.transform.TypeChecked

/**
 * <p>TODO #4: This class contains several errors that can be picked up by
 * static type checking. Uncomment the {@code @TypeChecked} annotation and then
 * fix the errors. Note that parameterised types usually need to be explicitly
 * declared as such.</p>
 */
//@TypeChecked
class BrokenCode {

    int sumNumbers(numbers) {
        def result = 0
        for (int i in number) {
            result += i
        }
        return result
    }

    BigDecimal calculateVat(BigDecimal value) {
        return (value * 0.2).setScale(2, RoundingMode.HALF_UP)
    }

    def mean(Collection<Integer> numbers) {
        return (numbers.sum() as int) / numbers.size()
    }

    BigDecimal median(Collection<Integer> numbers) {
        if (numbers.size() % 2 == 0) {
            // Even number of values
            def firstIndex = Math.floorDiv(numbers.size(), 2) - 1
            return mean(numbers.sort()[firstIndex..(firstIndex + 1)])
        }
        else {
            def index = Math.floorDiv(numbers.size(), 2)
            return numbers.sort().get(index)
        }
    }

    List reverseStrings(Collection strings) {
        def result = []
        for (str in strings) {
            result << str.revers()
        }
        return result
    }
}
