package io.viren.java8designpatterns.util;

import java.util.Objects;

public interface Predicate<T> {

    boolean test(T item);

    default Predicate<T> and(Predicate<T> another) {
        Objects.requireNonNull(another);
        return item -> this.test(item) && another.test(item);
    }

    default Predicate<T> or(Predicate<T> another) {
        Objects.requireNonNull(another);
        return item -> this.test(item) || another.test(item);
    }

    default Predicate<T> negate() {
        return item -> !test(item);
    }

    @SuppressWarnings("unused")
    static <T> Predicate<T> getFromJavaPredicate(java.util.function.Predicate<T> javaPredicate) {
        return javaPredicate::test;
    }

    static <T> java.util.function.Predicate<T> toJava8Predicate(Predicate<T> predicate) {
        return predicate::test;
    }
}
