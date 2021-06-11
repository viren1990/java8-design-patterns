package io.viren.java8designpatterns.registry;

import io.viren.java8designpatterns.factory.ShapeFactory;
import io.viren.java8designpatterns.model.Shape;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public interface SmartShapeRegistry {

    ShapeFactory<? extends Shape> buildShapeFactory(final String shape);

    static <T extends Shape> SmartShapeRegistry createRegistry(Consumer<Builder<T>> consumer, Function<String, ShapeFactory<T>> error) {
        Map<String, ShapeFactory<T>> map = new HashMap<>();
        Builder<T> shapeBuilder = map::putIfAbsent;
        consumer.accept(shapeBuilder);

        return shape -> map.computeIfAbsent(shape, error);
    }

}
