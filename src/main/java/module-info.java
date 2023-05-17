module com.example.park {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.park to javafx.fxml;
    exports com.example.park;
}