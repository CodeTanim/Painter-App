package edu.cuny.ccny.a5;

public class A5Diamond {
    private String color;
    private double x;
    private double y;
    private double dimension;

    public A5Diamond() {

        this(-1, -1, -1, "");
    }

    public A5Diamond(double x, double y, double dimension, String color) {
        this.x = x;
        this.y = y;
        this.dimension = dimension;
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }
}
