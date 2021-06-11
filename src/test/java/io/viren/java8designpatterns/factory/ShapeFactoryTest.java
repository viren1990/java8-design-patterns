package io.viren.java8designpatterns.factory;

import io.viren.java8designpatterns.model.Circle;
import io.viren.java8designpatterns.model.Rectangle;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

class ShapeFactoryTest {

    @Test
    void factory_test() {
        ShapeFactory<Circle> blueCircleFactory = ShapeFactory.createFactory(Circle::new, Color.BLUE);
        ShapeFactory<Rectangle> redRectangleFactory = ShapeFactory.createFactory(Rectangle::new, Color.RED);

        Circle circle = blueCircleFactory.get();
        Rectangle rectangle = redRectangleFactory.get();
        assertThat(circle.getColor()).isEqualTo(Color.BLUE);
        assertThat(rectangle.getColor()).isEqualTo(Color.RED);
    }

    @Test
    void factory_singleton_test() {
        ShapeFactory<Circle> circleFactory = ShapeFactory.createFactory(Circle::new);
        Circle circle = circleFactory.get();

        Circle anotherCircle = circleFactory.get();
        assertThat(circle).isNotEqualTo(anotherCircle);

        ShapeFactory<Circle> circleShapeSingletonFactory = ShapeFactory.createFactorySingleton(Circle::new);
        Circle circle1 = circleShapeSingletonFactory.get();
        Circle circle2 = circleShapeSingletonFactory.get();

        assertThat(circle1).isEqualTo(circle2);
    }
}