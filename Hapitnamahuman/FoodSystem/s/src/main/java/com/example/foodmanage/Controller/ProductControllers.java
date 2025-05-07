package com.example.foodmanage.Controller;

import com.example.foodmanage.ProductItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductControllers {

    @FXML private ImageView imageView;
    @FXML private Label nameAdd;
    @FXML private Spinner<Integer> onSpinner;
    @FXML private Button onAdd;
    @FXML private Label amountAdd;
    @FXML private Label priceLabel; // Make sure to add this in your FXML

    private ProductItem product;

    public void setData(ProductItem productItem) {
        this.product = productItem;
        nameAdd.setText(productItem.getName());
        loadProductImage(productItem.getImagePath());

        // Set price label
        amountAdd.setText(String.format("₱%.2f", productItem.getPrice()));

        // Set spinner range (1 to 10) and default value (1)
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        onSpinner.setValueFactory(valueFactory);

        // Calculate initial amount
        updateAmountLabel(1);

        // Add listener for spinner changes
        onSpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateAmountLabel(newVal));
    }

    private void loadProductImage(String imagePath) {
        if (getClass().getResource("/com/example/foodmanage/images/" + imagePath) != null) {
            Image image = new Image(getClass().getResourceAsStream("/com/example/foodmanage/images/" + imagePath));
            imageView.setImage(image);
        } else {
            imageView.setImage(null);
        }
    }

    private void updateAmountLabel(int quantity) {
        double amount = product.getPrice() * quantity;
        amountAdd.setText(String.format(" ₱%.2f", amount));
    }

    public Button getAddButton() {
        return onAdd;
    }

    public int getQuantity() {
        return onSpinner.getValue();
    }

    @FXML
    private void handleAddButtonAction() {
        System.out.println("Adding to order: " + product.getName() + " x" + getQuantity());
    }

    @FXML
    private void onMouseEntered() {
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0);");
    }

    @FXML
    private void onMouseExited() {
        imageView.setStyle("-fx-effect: none;");
    }
}
