module com.example.park {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires jdk.hotspot.agent;



    opens com.example.park to javafx.fxml;
    exports com.example.park;
}