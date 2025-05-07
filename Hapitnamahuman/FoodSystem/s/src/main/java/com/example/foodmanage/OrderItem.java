package com.example.foodmanage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class OrderItem {
    private final ProductItem product;
    private final IntegerProperty quantity;

    public OrderItem(ProductItem product, int quantity) {
        this.product = product;
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public ProductItem getProduct() {
        return product;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
}
