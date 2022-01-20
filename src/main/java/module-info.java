module com.example.connect4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.connect4 to javafx.fxml;
    exports com.example.connect4;
}