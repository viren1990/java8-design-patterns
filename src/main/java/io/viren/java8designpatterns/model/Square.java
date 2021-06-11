package io.viren.java8designpatterns.model;

import java.awt.*;

public class Square extends AbstractShape {

    protected Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("drawing square of color: " + this.getColor());
    }

    @Override
    public String toString() {
        return "Square{}";
    }
}
