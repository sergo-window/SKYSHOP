package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final int productCost;

    public SimpleProduct(String productName, int productCost) {
        super(productName);
        this.productCost = productCost;
    }

    @Override
    public int getProductCost() {
        return productCost;
    }

    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getDisplayInfo() {
        return getProductName() + ": " + getProductCost() + " руб.";
    }
}
