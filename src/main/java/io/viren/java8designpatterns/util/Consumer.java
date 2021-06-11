package io.viren.java8designpatterns.util;

import java.util.Objects;

@FunctionalInterface
public interface Consumer<T> {

    void accept(T element);

    default Consumer<T> andThen(Consumer<T> other) {
        Objects.requireNonNull(other);
        return item -> {
            this.accept(item);
            other.accept(item);
        };
    }
}
