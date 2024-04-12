module com.example.alap {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.alap to javafx.fxml;
    exports com.example.alap;
}