package edu.cuny.ccny.a5.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Diamond extends Rectangle {

    public Diamond(double centerX, double centerY, double dimention, Color color) {

        super(centerX, centerY, dimention + 4, dimention + 4);
        super.setFill(color);
        super.rotateProperty().set(45);
    }
}
