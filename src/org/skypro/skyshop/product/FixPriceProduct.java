package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 200;

    public FixPriceProduct(String productName) {
        super(productName);
    }

    @Override
    public int getProductCost() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getDisplayInfo() {
        return getProductName() + ": Фиксированная цена " + FIX_PRICE + " руб.";
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