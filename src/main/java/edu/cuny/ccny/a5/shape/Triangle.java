package edu.cuny.ccny.a5.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Triangle extends Polygon {

    public Triangle(double centerX, double centerY, double dimension, Color color) {
        this(centerX, centerY, dimension / 2 + 4, dimension / 2 + 4, color);
    }

    private Triangle(double centerX, double centerY, double halfBase, double halfHeight, Color color) {

        super(centerX + halfBase, centerY + halfHeight,
                centerX, centerY - halfHeight,
                centerX - halfBase, centerY + halfHeight
        );

        super.setFill(color);
    }

}
