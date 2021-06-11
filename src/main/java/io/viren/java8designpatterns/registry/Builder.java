package io.viren.java8designpatterns.registry;

import io.viren.java8designpatterns.factory.ShapeFactory;
import io.viren.java8designpatterns.model.Shape;

public interface Builder<T extends Shape> {

    void register(String shape, ShapeFactory<T> factory);
}
