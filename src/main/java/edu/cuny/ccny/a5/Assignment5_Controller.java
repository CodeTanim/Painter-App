package edu.cuny.ccny.a5;

import edu.cuny.ccny.a5.shape.Diamond;
import edu.cuny.ccny.a5.shape.Triangle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;



public class Assignment5_Controller {




    private enum DrawShape {
        CIRCLE,
        TRIANGLE,
        DIAMOND
    }

    private enum PenSize {

        SMALL(4),
        MEDIUM(8),
        LARGE(12),
        XLARGE(16);

        private final int radius;

        PenSize(int radius) {
            this.radius = radius;
        }
    }

    private enum DrawColor {

        BLACK(Color.BLACK),
        RED(Color.RED),
        GREEN(Color.GREEN),
        BLUE(Color.BLUE);

        private final Color color;

        DrawColor(Color color) {
            this.color = color;
        }
    }

    @FXML
    private Button btnExit;

    @FXML
    private Button btnDiamondXml;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUndo;

    @FXML
    private Pane panelDraw;

    @FXML
    private RadioButton rBtnBlack;

    @FXML
    private RadioButton rBtnBlue;

    @FXML
    private RadioButton rBtnCircle;

    @FXML
    private RadioButton rBtnTriangle;

    @FXML
    private RadioButton rBtnDiamond;

    @FXML
    private RadioButton rBtnGreen;

    @FXML
    private RadioButton rBtnLarge;

    @FXML
    private RadioButton rBtnXLarge;

    @FXML
    private RadioButton rBtnMedium;

    @FXML
    private RadioButton rBtnRed;

    @FXML
    private RadioButton rBtnSmall;

    @FXML
    private ToggleGroup tgColor;

    @FXML
    private ToggleGroup tgPenSize;

    @FXML
    private ToggleGroup tgShape;

    private DrawColor drawColor = DrawColor.BLACK;
    private PenSize penSize = PenSize.MEDIUM;
    private DrawShape drawShape = DrawShape.CIRCLE;

    @FXML
    void colorChangeEventHandler() {
        if (rBtnBlack.isSelected())
            drawColor = DrawColor.BLACK;
        else if (rBtnGreen.isSelected())
            drawColor = DrawColor.GREEN;
        else if (rBtnRed.isSelected())
            drawColor = DrawColor.RED;
        else
            drawColor = DrawColor.BLUE;
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {

        // ensure that the circle's perimeter doesn't overflow the drawing panels
        if (panelDraw.getLayoutBounds().contains(event.getX() - penSize.radius, event.getY() - penSize.radius) &&
                panelDraw.getLayoutBounds().contains(event.getX() + penSize.radius, event.getY() + penSize.radius))
            if (drawShape == DrawShape.CIRCLE)
                panelDraw.getChildren().add(new Circle(event.getX(), event.getY(), penSize.radius, drawColor.color));

            else if(drawShape == DrawShape.TRIANGLE)
                panelDraw.getChildren().add(new Triangle(event.getX(), event.getY(), penSize.radius, drawColor.color));

            else if(drawShape == DrawShape.DIAMOND)
                panelDraw.getChildren().add(new Diamond(event.getX(), event.getY(), penSize.radius, drawColor.color));

            else
                throw new RuntimeException("Invalid drawing shape");

    }

    @FXML
    void penSizeChangeEventHandler() {

        if (rBtnSmall.isSelected())
            penSize = PenSize.SMALL;
        else if (rBtnMedium.isSelected())
            penSize = PenSize.MEDIUM;
        else if(rBtnLarge.isSelected())
            penSize = PenSize.LARGE;
        else
            penSize = PenSize.XLARGE;
    }

    @FXML
    void shapeChangeEventHandler() {

        if (rBtnCircle.isSelected())
            drawShape = DrawShape.CIRCLE;

        else if(rBtnTriangle.isSelected())
            drawShape = DrawShape.TRIANGLE;

        else if(rBtnDiamond.isSelected())
            drawShape = DrawShape.DIAMOND;
        else
            throw new RuntimeException("Invalid shape selection");
    }

    @FXML
    void resetButtonEventHandler() {

        panelDraw.getChildren().clear();

        rBtnBlack.setSelected(true);
        rBtnSmall.setSelected(true);

        colorChangeEventHandler();
        penSizeChangeEventHandler();

        rBtnCircle.setSelected(true);
    }

    @FXML
    void undoButtonEventHandler() {

        if (!panelDraw.getChildren().isEmpty())
            panelDraw.getChildren().remove(panelDraw.getChildren().size() - 1);
    }

    @FXML
   void diamondButtonEventHandler() throws IOException {


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an XML File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(btnDiamondXml.getScene().getWindow());
        String fileName;
       if(file != null)
            fileName = file.getName();
       else {
            System.out.println("No file was selected.");
            return;
        }

        String pattern = "[A-Z][a-zA-Z0-9.]{2,}(?i)XML";
        if (fileName.matches(pattern)) {
            //loop while being drawn
            XMLEncoder xmlEncoder = new XMLEncoder(Files.newOutputStream(file.toPath()));


            for(Node obj : panelDraw.getChildren()) {

                if (obj instanceof Diamond d) {
                    A5Diamond obj2 = new A5Diamond(d.getX(), d.getY(), d.getWidth(), d.getFill().toString());
                    xmlEncoder.writeObject(obj2);

               }
            }
            xmlEncoder.flush();
            xmlEncoder.close();
        }

        else {
            System.out.println("Invalid file. Try choosing another file.");
        }


    }


    @FXML
    void exitButtonEventHandler () {
        System.exit(0);
    }

    @FXML
    void initialize() {

        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert btnUndo != null : "fx:id=\"btnUndo\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert panelDraw != null : "fx:id=\"panelDraw\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnBlack != null : "fx:id=\"rBtnBlack\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnBlue != null : "fx:id=\"rBtnBlue\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnCircle != null : "fx:id=\"rBtnCircle\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnGreen != null : "fx:id=\"rBtnGreen\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnLarge != null : "fx:id=\"rBtnLarge\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnXLarge != null : "fx:id=\"rBtnXlarge\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnMedium != null : "fx:id=\"rBtnMedium\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnRed != null : "fx:id=\"rBtnRed\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert rBtnSmall != null : "fx:id=\"rBtnSmall\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert tgColor != null : "fx:id=\"tgColor\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert tgPenSize != null : "fx:id=\"tgPenSize\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert tgShape != null : "fx:id=\"tgShape\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";
        assert btnDiamondXml != null : "fx:id=\"btnDiamondXml\" was not injected: check your FXML file 'PainterAssignment5.fxml'.";

    }
}