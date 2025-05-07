package com.example.foodmanage;

import javafx.beans.property.*;

public class ProductItem {
    private final StringProperty name;
    private final DoubleProperty price;
    private final StringProperty category;
    private final String imagePath;
    private final StringProperty id;  // Added id field

    // Constructor to initialize product details
    public ProductItem(String name, double price, String category, String imagePath, String id) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleStringProperty(category);
        this.imagePath = imagePath;
        this.id = new SimpleStringProperty(id); // Initialize id
    }

    // Getter for name as StringProperty
    public StringProperty nameProperty() {
        return name;
    }

    // Getter for price
    public double getPrice() {
        return price.get();
    }

    // Getter for category as StringProperty
    public StringProperty categoryProperty() {
        return category;
    }

    // Getter for category as plain string
    public String getCategory() {
        return category.get();
    }

    // Getter for imagePath
    public String getImagePath() {
        return imagePath;
    }

    // Getter for the name as a plain string
    public String getName() {
        return name.get();
    }

    // Getter for id
    public String getId() {
        return id.get();
    }
}
