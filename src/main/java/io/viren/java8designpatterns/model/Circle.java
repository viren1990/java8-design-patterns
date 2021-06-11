package io.viren.java8designpatterns.model;


import java.awt.*;

public class Circle extends AbstractShape {

    public Circle(Color color) {
        super(color);
    }

    public Circle() {
        super(Color.BLUE);
    }

    @Override
    public void draw() {
        System.out.println(String.format("Drawing %s colored circle", this.getColor().toString()));
    }

    @Override
    public String toString() {
        return "Circle{}";
    }
}
