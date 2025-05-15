package org.skypro.skyshop.product;

import org.skypro.skyshop.article.Searchable;

public abstract class Product implements Searchable {
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

    @Override
    public String getSearchTerm() {
        return productName;
    }

    @Override
    public String getSearchableObjectType() {
        return "PRODUCT";
    }

    @Override
    public String toString() {
        return "Товар: '" + productName + "'";
    }
}