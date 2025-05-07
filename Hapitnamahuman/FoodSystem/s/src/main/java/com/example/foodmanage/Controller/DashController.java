package com.example.foodmanage.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import java.io.IOException;

public class DashController {

    @FXML
    private BorderPane hootPane;

    @FXML
    private Button si_Home;

    @FXML
    private Button si_Menu;

    @FXML
    private Button si_Special;

    @FXML
    private Button si_BtnLogin;

    @FXML
    private TextField si_Search;

    @FXML
    private void onHome() {
        loadUI("Home.fxml");
    }

    @FXML
    private void onMenu() {
        loadUI("hello-view.fxml");
    }

    @FXML
    private void onSpecial() {
        loadUI("Special.fxml");
    }

    @FXML
    private void onContact() {
        loadUI("Contact.fxml");
    }

    @FXML
    private void Login() {
        System.out.println("Login button clicked. Implement login logic here.");
    }

    private void loadUI(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/foodmanage/" + fxmlFile));
            Node node = loader.load();
            hootPane.setCenter(node);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load: " + fxmlFile);
        }
    }
}
