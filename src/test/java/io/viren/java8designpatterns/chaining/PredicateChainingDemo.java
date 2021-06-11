package io.viren.java8designpatterns.chaining;

import io.viren.java8designpatterns.util.Predicate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.viren.java8designpatterns.util.Predicate.toJava8Predicate;
import static org.assertj.core.api.Assertions.assertThat;

public class PredicateChainingDemo {

    private static final List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private static final List<Integer> beyondConstraint = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

    @Test
    public void test_predicate_chaining() {
        Predicate<Integer> anyNegative = item -> item < 0;
        Predicate<Integer> anyGT10 = item -> item > 10;

        Predicate<Integer> composedPredicate = anyNegative.or(anyGT10);
        assertThat(integers.stream().anyMatch(toJava8Predicate(composedPredicate))).isEqualTo(false);
        assertThat(beyondConstraint.stream().anyMatch(toJava8Predicate(composedPredicate))).isEqualTo(true);

        System.out.println("Test Negation");

        java.util.function.Predicate<Integer> negatePredicate = toJava8Predicate(composedPredicate.negate());
        assertThat(integers.stream().anyMatch(negatePredicate)).isEqualTo(true);
    }

    @Test
    void test_and() {
        Predicate<Integer> sqrtInSourcePredicate = item -> integers.contains((int) Math.sqrt((double) item));
        Predicate<Integer> divisibleBy9 = item -> item % 9 == 0;
        assertThat(integers.stream().anyMatch(toJava8Predicate(sqrtInSourcePredicate.and(divisibleBy9)))).isEqualTo(true);
    }
}
