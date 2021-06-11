package io.viren.java8designpatterns.factory;

import io.viren.java8designpatterns.model.Shape;

import java.util.function.Function;
import java.util.function.Supplier;

public interface ShapeFactory<T extends Shape> extends Supplier<T> {

    static <T extends Shape> ShapeFactory<T> createFactory(Supplier<T> supplier) {
        return supplier::get;
    }

    static <T extends Shape> ShapeFactory<T> createFactorySingleton(Supplier<T> supplier) {
        T item = supplier.get();
        return () -> item;
    }

    static <T extends Shape, V> ShapeFactory<T> createFactory(Function<V, T> function, V param) {
        return () -> function.apply(param);
    }
}
