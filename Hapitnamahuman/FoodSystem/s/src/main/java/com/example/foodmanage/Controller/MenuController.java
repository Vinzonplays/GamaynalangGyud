package com.example.foodmanage.Controller;

import com.example.foodmanage.OrderItem;
import com.example.foodmanage.ProductItem;
import com.example.foodmanage.Repositories.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MenuController {

    @FXML
    private FlowPane raatPane;
    @FXML
    private TextField si_Search;
    @FXML
    private Button si_Food, si_Drinks, si_Coffee, si_Snack, si_Desert;
    @FXML
    private Button si_Clear, si_Checkout;
    @FXML
    private Label onTotal, orderStatusLabel;
    @FXML
    private TableView<OrderItem> onTableview;
    @FXML
    private TableColumn<OrderItem, String> foodItemColumn;
    @FXML
    private TableColumn<OrderItem, Integer> foodQuantityColumn;
    @FXML
    private TableColumn<OrderItem, Double> foodTotalColumn;
    @FXML
    private TableColumn<OrderItem, Void> foodRemoveColumn;

    private double totalAmount = 0.0;

    @FXML
    private void onFood() {
        displayProducts("Food");
    }

    @FXML
    private void onDrinks() {
        displayProducts("Drinks");
    }

    @FXML
    private void onCoffee() {
        displayProducts("Coffee");
    }

    @FXML
    private void onSnack() {
        displayProducts("Snack");
    }

    @FXML
    private void onDessert() {
        displayProducts("Dessert");
    }

    @FXML
    private void initialize() {
        si_Food.setOnAction(e -> displayProducts("Food"));
        si_Drinks.setOnAction(e -> displayProducts("Drinks"));
        si_Coffee.setOnAction(e -> displayProducts("Coffee"));
        si_Snack.setOnAction(e -> displayProducts("Snack"));
        si_Desert.setOnAction(e -> displayProducts("Dessert"));

        si_Search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterProducts(newValue);
        });

        si_Clear.setOnAction(e -> clearOrder());
        si_Checkout.setOnAction(e -> checkout());

        onTableview.setEditable(true);

        foodItemColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getProduct().getName())
        );

        foodQuantityColumn.setCellValueFactory(cellData ->
                cellData.getValue().quantityProperty().asObject()
        );

        foodTotalColumn.setCellValueFactory(cellData -> {
            double total = cellData.getValue().getProduct().getPrice() * cellData.getValue().getQuantity();
            return new javafx.beans.property.SimpleDoubleProperty(total).asObject();
        });

        foodQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        foodQuantityColumn.setOnEditCommit(event -> {
            OrderItem item = event.getRowValue();
            item.setQuantity(event.getNewValue());
            updateTotal();
        });

        // Remove Button Column
        foodRemoveColumn.setCellFactory(param -> {
            TableCell<OrderItem, Void> cell = new TableCell<OrderItem, Void>() {
                private final Button removeButton = new Button("Remove");

                {
                    removeButton.setOnAction(e -> {
                        OrderItem item = getTableRow().getItem();
                        if (item != null) {
                            onTableview.getItems().remove(item);
                            updateTotal();
                            showOrderPlacedMessage();
                        }
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(removeButton);
                    }
                }
            };
            return cell;
        });
    }

    private void updateTotal() {
        totalAmount = onTableview.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        onTotal.setText("Total: ₱" + String.format("%.2f", totalAmount));
    }

    private void displayProducts(String category) {
        ProductRepository repository = new ProductRepository();
        List<ProductItem> products = repository.getAllProductsByCategory(category);
        raatPane.getChildren().clear();

        for (ProductItem item : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/foodmanage/Product.fxml"));
                Node productNode = loader.load();
                ProductControllers controller = loader.getController();
                controller.setData(item);

                controller.getAddButton().setOnAction(e -> addToOrder(item, controller.getQuantity()));
                raatPane.getChildren().add(productNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void filterProducts(String searchQuery) {
        ProductRepository repository = new ProductRepository();
        List<ProductItem> filteredProducts = repository.getAllProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());

        raatPane.getChildren().clear();

        if (filteredProducts.isEmpty()) {
            Label noResultsLabel = new Label("No products found");
            raatPane.getChildren().add(noResultsLabel);
        } else {
            for (ProductItem item : filteredProducts) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/foodmanage/Product.fxml"));
                    Node productNode = loader.load();
                    ProductControllers controller = loader.getController();
                    controller.setData(item);

                    controller.getAddButton().setOnAction(e -> addToOrder(item, controller.getQuantity()));
                    raatPane.getChildren().add(productNode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addToOrder(ProductItem product, int quantity) {
        if (quantity <= 0) {
            return;
        }

        OrderItem orderItem = new OrderItem(product, quantity);

        for (OrderItem existingItem : onTableview.getItems()) {
            if (existingItem.getProduct().getId().equals(product.getId())) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                updateTotal();
                showOrderPlacedMessage();
                return;
            }
        }

        onTableview.getItems().add(orderItem);
        updateTotal();
        showOrderPlacedMessage();
    }

    private void showOrderPlacedMessage() {
        StringBuilder summary = new StringBuilder("Order Status:\n\n");
        for (OrderItem item : onTableview.getItems()) {
            summary.append(String.format("- %s x%d = ₱%.2f\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getPrice() * item.getQuantity()));
        }
        summary.append(String.format("\nTotal: ₱%.2f", totalAmount));
        orderStatusLabel.setText(summary.toString());
    }

    private void checkout() {
        if (onTableview.getItems().isEmpty()) {
            orderStatusLabel.setText("No items to checkout.");
            return;
        }

        StringBuilder summary = new StringBuilder("Order Placed Successfully!\n\n");
        summary.append("Items:\n");
        for (OrderItem item : onTableview.getItems()) {
            summary.append(String.format("- %s x%d = ₱%.2f\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getPrice() * item.getQuantity()));
        }
        summary.append(String.format("\nTotal: ₱%.2f", totalAmount));
        orderStatusLabel.setText(summary.toString());
    }

    private void clearOrder() {
        onTableview.getItems().clear();
        totalAmount = 0.0;
        onTotal.setText("Total: ₱0.00");
        orderStatusLabel.setText("");
    }
}
