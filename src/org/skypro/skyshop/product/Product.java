package org.skypro.skyshop.product;

import org.skypro.skyshop.article.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {
    private final String productName;

    public Product(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("\nОШИБКА! (некорректное название продукта)");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(getSearchableObjectName(), ((Searchable) o).getSearchableObjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSearchableObjectName());
    }
}