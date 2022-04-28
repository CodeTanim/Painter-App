module edu.cuny.ccny.a5.assignment_5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens edu.cuny.ccny.a5 to javafx.fxml;
    exports edu.cuny.ccny.a5;
    exports edu.cuny.ccny.a5.shape;
    opens edu.cuny.ccny.a5.shape to javafx.fxml;
}