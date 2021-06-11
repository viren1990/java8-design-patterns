package io.viren.java8designpatterns.registry;

import io.viren.java8designpatterns.factory.ShapeFactory;
import io.viren.java8designpatterns.model.Circle;
import io.viren.java8designpatterns.model.Rectangle;
import io.viren.java8designpatterns.model.Shape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdinaryShapeRegistryTest {


    @Test
    void test_ordinary_registry() {
        OrdinaryShapeRegistry shapeRegistry = new OrdinaryShapeRegistry();

        ShapeFactory<? extends Shape> circleFactory = shapeRegistry.getShapeFactory("circle");
        Shape shape = circleFactory.get();
        assertTrue(shape instanceof Circle);

        ShapeFactory<? extends Shape> rectangleFactory = shapeRegistry.getShapeFactory("rectangle");
        Shape rectangle = rectangleFactory.get();
        assertTrue(rectangle instanceof io.viren.java8designpatterns.model.Rectangle);

        assertThrows(IllegalArgumentException.class, () -> shapeRegistry.getShapeFactory("square"));
    }
}