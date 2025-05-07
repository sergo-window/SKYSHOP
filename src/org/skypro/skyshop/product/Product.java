package org.skypro.skyshop.product;

public abstract class Product {
    private final String productName;

    public Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public abstract int getProductCost();

    public abstract boolean isSpecial();

    public abstract String getDisplayInfo();
}
