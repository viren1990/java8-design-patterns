package io.viren.java8designpatterns.model;

import java.awt.*;

public abstract class AbstractShape implements Shape {

    private final Color color;

    protected AbstractShape(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
