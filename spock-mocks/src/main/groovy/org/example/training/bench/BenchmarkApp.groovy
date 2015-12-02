package org.example.training.bench

import groovyx.gbench.BenchmarkList

/**
 * <p>TODO #5: This exercise doesn't require any coding on your part. Simply run
 * this class and look at the output. You'll be able to compare how much time
 * Groovy spends performing a quicksort relative to the same algorithm in Java.
 * Once you've done that, uncomment the {@code @CompileStatic} annotation from
 * {@link GroovyQuickSort} and run the exercise again. Notice a difference?
 * Finally, replace {@code @CompileStatic} with {@code @TypeChecked} and run
 * the exercise again.</p>
 * <p>Performance is the main reason to use {@code @CompileStatic}, but do be
 * aware that the behaviour of the underlying code is different from normal
 * Groovy in a few edge cases. It's also worth bearing in mind that this kind
 * of performance boost is only apparent in computationally expensive tasks.
 * {@code @TypeChecked} has the same behaviour and performance characteristics
 * of dynamic Groovy at runtime. That's because it generates exactly the same
 * bytecode. The only difference is the compile-time type checking.</p>
 */
class BenchmarkApp {

    static void main(String[] args) {
        def arraySize = args.size() == 1 ? args[0] as int : 100_000

        benchmark(generateRandomNumbers(arraySize) as int[]).prettyPrint()
    }

    static BenchmarkList benchmark(int[] numbers) {
        return benchmark {
            'Groovy quicksort' {
                GroovyQuickSort.quickSort(numbers.clone())
            }
            'Java quicksort' {
                JavaQuickSort.quickSort(numbers.clone())
            }
        }
    }

    private static List<Integer> generateRandomNumbers(int length) {
        List<Integer> result = new ArrayList(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.add(random.nextInt(length));
        }

        return result;
    }
}
