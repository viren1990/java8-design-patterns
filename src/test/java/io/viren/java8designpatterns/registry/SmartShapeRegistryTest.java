package io.viren.java8designpatterns.registry;

import io.viren.java8designpatterns.factory.ShapeFactory;
import io.viren.java8designpatterns.model.Circle;
import io.viren.java8designpatterns.model.Rectangle;
import io.viren.java8designpatterns.model.Shape;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static io.viren.java8designpatterns.factory.ShapeFactory.createFactorySingleton;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
class SmartShapeRegistryTest {

    @Test
    void test_smart_registry() {
        Consumer<Builder<Shape>> consumer = builder -> builder.register("rectangle", createFactorySingleton(Rectangle::new));
        Consumer<Builder<Shape>> consumer2 = builder -> builder.register("circle", createFactorySingleton(Circle::new));

        Consumer<Builder<Shape>> jointConsumer = consumer.andThen(consumer2);

        SmartShapeRegistry rectangleRegistry = SmartShapeRegistry.createRegistry(jointConsumer, s -> {
            throw new IllegalArgumentException("unknown shape " + s);
        });
        ShapeFactory<Rectangle> rectangleShapeFactory = (ShapeFactory<Rectangle>) rectangleRegistry.buildShapeFactory("rectangle");
        ShapeFactory<Circle> circleShapeFactory = (ShapeFactory<Circle>) rectangleRegistry.buildShapeFactory("circle");

        Rectangle rectangle = rectangleShapeFactory.get();
        Rectangle rectangle2 = rectangleShapeFactory.get();
        assertEquals(rectangle, rectangle2);
        Circle circle = circleShapeFactory.get();
        assertNotNull(rectangle);
        assertNotNull(circle);

        assertThrows(IllegalArgumentException.class, () -> rectangleRegistry.buildShapeFactory("square"));
    }
}