package io.viren.java8designpatterns.registry;

import io.viren.java8designpatterns.factory.ShapeFactory;
import io.viren.java8designpatterns.model.Circle;
import io.viren.java8designpatterns.model.Rectangle;
import io.viren.java8designpatterns.model.Shape;

import java.util.Objects;

/*
a Kind of factory but returns object on the basis of a key.
 */
public class OrdinaryShapeRegistry {

    /*
    downside of this way is , registry has to know of all the keys on compile time. And Bizarre if this class is part of
    a public API , client can never scale itself and has to stuck with just two shapes defined in here.
     */
    public ShapeFactory<? extends Shape> getShapeFactory(final String shape) {
        Objects.requireNonNull(shape);

        switch (shape) {
            case "circle":
                return Circle::new;
            case "rectangle":
                return Rectangle::new;
            default:
                throw new IllegalArgumentException("Unknown Shape: " + shape);
        }
    }

}
