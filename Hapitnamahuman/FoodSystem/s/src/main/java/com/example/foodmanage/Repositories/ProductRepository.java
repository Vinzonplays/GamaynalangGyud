package com.example.foodmanage.Repositories;

import com.example.foodmanage.ProductItem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class ProductRepository {

    private List<ProductItem> allProducts;

    public ProductRepository() {
        allProducts = new ArrayList<>();
        allProducts.add(new ProductItem("Adobo sa Gata", 5.99, "Food", "adobo sa gata.jpg", "1"));
        allProducts.add(new ProductItem("Sinigang na Baboy", 2.99, "Food", "sinigang na baboy.png", "2"));
        allProducts.add(new ProductItem("Inihaw na Liempo", 1.99, "Food", "inihaw na liempo.jpg", "3"));
        allProducts.add(new ProductItem("Beef Steak", 7.99, "Food", "beef steak.jpg", "4"));
        allProducts.add(new ProductItem("Chicken Inasal", 5.99, "Food", "chicken inasal.jpg", "5"));
        allProducts.add(new ProductItem("Bangus Ala Pobre", 5.99, "Food", "bangus ala pobre.jpg", "6"));
        allProducts.add(new ProductItem("Vegetarian Kare-Kare", 5.99, "Food", "vegetarian kare kare.jpg", "7"));
        allProducts.add(new ProductItem("Kapeng Barako", 5.99, "Coffee", "kapeng barako.jpg", "8"));
        allProducts.add(new ProductItem("Iced Coffee Barako", 5.99, "Coffee", "ice coffe barako.jpg", "9"));
        allProducts.add(new ProductItem("Cafe Latte", 5.99, "Coffee", "cafe latte.png", "10"));
        allProducts.add(new ProductItem("Cafe Mocha", 5.99, "Coffee", "cafe mocha.jpg", "11"));
        allProducts.add(new ProductItem("Caramel Macchiato", 5.99, "Coffee", "caramel macchiato.jpg", "12"));
        allProducts.add(new ProductItem("Spanish Latte", 5.99, "Coffee", "spanish latte.png", "13"));
        allProducts.add(new ProductItem("Cold Brew Coffee", 5.99, "Coffee", "cold brew coffe.jpg", "14"));
        allProducts.add(new ProductItem("Sago't Gulaman", 1.99, "Drinks", "sago't gulaman.jpg", "15"));
        allProducts.add(new ProductItem("Fresh Calamansi Juice", 1.99, "Drinks", "fresh calamansi juice.jpg", "16"));
        allProducts.add(new ProductItem("Iced Tea", 1.99, "Drinks", "iced tea.jpg", "17"));
        allProducts.add(new ProductItem("Hot Chocolate", 1.99, "Drinks", "hot chocolate.jpg", "18"));
        allProducts.add(new ProductItem("Buko Juice", 1.99, "Drinks", "buko juice.jpg", "19"));
        allProducts.add(new ProductItem("Mango Shake", 1.99, "Drinks", "mango juice.jpg", "20"));
        allProducts.add(new ProductItem("Pancit Palabok", 1.99, "Snack", "pancit palabok.jpg", "21"));
        allProducts.add(new ProductItem("Pancit Canton", 1.99, "Snack", "pancit canton.jpg", "22"));
        allProducts.add(new ProductItem("Arroz Caldo", 1.99, "Snack", "arroz caldo.jpg", "23"));
        allProducts.add(new ProductItem("Empanada Ilocos Style", 1.99, "Snack", "ilocos impanada.jpg", "24"));
        allProducts.add(new ProductItem("Puto", 1.99, "Snack", "puto.jpg", "25"));
        allProducts.add(new ProductItem("Kutsinta", 1.99, "Snack", "kutsinta.jpg", "26"));
        allProducts.add(new ProductItem("Suman sa Lihiya", 1.99, "Snack", "suman lihiya'.jpg", "27"));
        allProducts.add(new ProductItem("Halo-Halo Supreme", 1.99, "Dessert", "HALO-HALO-SUPREME.jpg", "28"));
        allProducts.add(new ProductItem("Leche Flan", 1.99, "Dessert", "leche flan.jpg", "29"));
        allProducts.add(new ProductItem("Bibingka", 1.99, "Dessert", "bibingka.jpg", "30"));
        allProducts.add(new ProductItem("Puto Bumbong", 1.99, "Dessert", "puto bumbong.jpg", "31"));
        allProducts.add(new ProductItem("Cassava Cake", 1.99, "Dessert", "cassava cake.jpg", "32"));
        allProducts.add(new ProductItem("Buko Pandan Salad", 1.99, "Dessert", "buko pandan.jpg", "33"));

    }

    public List<ProductItem> getAllProducts() {
        return allProducts;
    }

    public List<ProductItem> getAllProductsByCategory(String category) {
        return allProducts.stream()
                .filter(p -> p.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}
