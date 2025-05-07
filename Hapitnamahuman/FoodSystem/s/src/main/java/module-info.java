module com.example.foodmanage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.compiler;


    opens com.example.foodmanage to javafx.fxml;
    exports com.example.foodmanage;
    exports com.example.foodmanage.Controller;
    opens com.example.foodmanage.Controller to javafx.fxml;
    exports com.example.foodmanage.Repositories;
    opens com.example.foodmanage.Repositories to javafx.fxml;
}