package org.skypro.skyshop.productbasket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products;
    private int count;

    public ProductBasket(int capacity) {
        this.products = new Product[capacity];
        this.count = 0;
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Ошибка: попытка добавить null");
            return;
        }
        if (count < products.length) {
            products[count] = product;
            count++;
        } else {
            System.out.println("НЕВОЗМОЖНО ДОБАВИТЬ ПРОДУКТ!");
        }
    }

    public void printBasket() {
        if (count == 0) {
            System.out.println("В корзине пусто");
            return;
        }

        for (int i = 0; i < count; i++) {
            Product product = products[i];
            System.out.printf("%d. %s - %d руб.%n",
                    i + 1,
                    product.getProductName(),
                    product.getProductCost()
            );
        }

        System.out.printf("Общая стоимость: %d руб.%n", getTotalCost());
    }

    public int getTotalCost() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getProductCost();
        }
        return total;
    }

    public void searchAndPrintProduct(String productName) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (products[i].getProductName().equals(productName)) {
                System.out.printf("Найден продукт: %s - %d руб. (позиция %d)%n",
                        productName,
                        products[i].getProductCost(),
                        i + 1);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Продукт '" + productName + "' не найден в корзине");
        }
    }

    public void clearBasket() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
        System.out.println("Корзина очищена");
    }
}
