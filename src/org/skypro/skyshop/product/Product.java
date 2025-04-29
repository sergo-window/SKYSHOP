package org.skypro.skyshop.product;

public class Product {
    private final String productName;
    private final int productCost;

    public Product(String productName, int productCost) {
        this.productName = productName;
        this.productCost = productCost;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductCost() {
        return productCost;
    }
}
