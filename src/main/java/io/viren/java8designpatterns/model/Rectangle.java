package io.viren.java8designpatterns.model;

import java.awt.*;

public class Rectangle extends AbstractShape {

    public Rectangle(Color color) {
        super(color);
    }

    public Rectangle() {
        super(Color.BLACK);
    }

    @Override
    public void draw() {
        System.out.println(String.format("Drawing %s colored rectangle", this.getColor().toString()));
    }

    @Override
    public String toString() {
        return "Rectangle{}";
    }
}
