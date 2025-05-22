package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int baseCost;
    private final int discountPercent;

    public DiscountedProduct(String productName, int baseCost, int discountPercent) {
        super(productName);
        if (baseCost <= 0) {
            throw new IllegalArgumentException("\nОШИБКА! (некорректная базовая цена продукта)");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("\nОШИБКА! (некорректный процент скидки на продукт)");
        }
        this.baseCost = baseCost;
        this.discountPercent = discountPercent;
    }

    @Override
    public int getProductCost() {
        return (int) (baseCost * (1 - discountPercent / 100.00));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getDisplayInfo() {
        return getProductName() + ": " + getProductCost() + " руб. (" + discountPercent + "%)";
    }

    @Override
    public String getSearchTerm() {
        return getStringRepresentation();
    }

    @Override
    public String getSearchableObjectType() {
        return "";
    }

    @Override
    public String getSearchableObjectName() {
        return "";
    }
}