package org.skypro.skyshop.productbasket;

import org.skypro.skyshop.product.Product;

import java.util.*;


public class ProductBasket {
    private final TreeMap<String, LinkedList<Product>> productsMap;

    public ProductBasket() {
        this.productsMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Ошибка: попытка добавить null");
            return;
        }
        productsMap.computeIfAbsent(product.getProductName(), k -> new LinkedList<>()).add(product);
    }

    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        int specialCount = 0;
        int totalItems = 0;

        for (List<Product> products : productsMap.values()) {
            for (Product product : products) {
                System.out.println(product.getDisplayInfo());
                if (product.isSpecial()) {
                    specialCount++;
                }
                totalItems++;
            }
        }

        System.out.println("ИТОГО: " + getTotalCost() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
        System.out.println("Всего товаров: " + totalItems);
    }

    public int getTotalCost() {
        int total = 0;
        for (List<Product> products : productsMap.values()) {
            for (Product product : products) {
                total += product.getProductCost();
            }
        }
        return total;
    }

    public void searchAndPrintProduct(String productName) {
        LinkedList<Product> products = productsMap.get(productName);
        if (products == null || products.isEmpty()) {
            System.out.println("Продукт '" + productName + "' не найден в корзине");
            return;
        }

        System.out.println("Найдены продукты '" + productName + "':");
        int position = 1;
        for (Product product : products) {
            System.out.printf("%d. %s - %d руб.%n",
                    position++,
                    product.getDisplayInfo(),
                    product.getProductCost());
        }
    }

    public void clearBasket() {
        productsMap.clear();
        System.out.println("Корзина очищена");
    }

    public List<Product> removeProduct(String name) {
        LinkedList<Product> removedProducts = productsMap.remove(name);
        if (removedProducts == null) {
            System.out.println("Список пуст");
            return Collections.emptyList();
        }
        return removedProducts;
    }
}