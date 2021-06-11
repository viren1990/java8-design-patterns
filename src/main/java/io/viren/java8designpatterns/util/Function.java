package io.viren.java8designpatterns.util;

import java.util.Objects;

@FunctionalInterface
public interface Function<T, R> {

    R apply(T t);

    default <V> Function<T, V> andThen(Function<R, V> another) {
        Objects.requireNonNull(another);
        return item -> {
            R r = this.apply(item);
            return another.apply(r);
        };
    }

    default <V> Function<V, R> compose(Function<V, T> other) {
        Objects.requireNonNull(other);
        return v -> {
            T t = other.apply(v);
            return this.apply(t);
        };
    }

}
