package org.skypro.skyshop.productbasket;

import org.skypro.skyshop.product.Product;

import java.util.*;

import java.util.stream.IntStream;

public class ProductBasket {
    private final Map<String, LinkedList<Product>> productsMap;

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

        productsMap.values().stream()
                .flatMap(List::stream)
                .forEach(product -> System.out.println(product.getDisplayInfo()));

        System.out.println("ИТОГО: " + getTotalCost() + " руб.");
        System.out.println("Специальных товаров: " + getSpecialCount());
        System.out.println("Всего товаров: " + getTotalItemCount());
    }

    public int getTotalCost() {
        return productsMap.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getProductCost)
                .sum();
    }

    private long getSpecialCount() {
        return productsMap.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }

    private long getTotalItemCount() {
        return productsMap.values().stream()
                .mapToLong(List::size)
                .sum();
    }

    public void searchAndPrintProduct(String productName) {
        LinkedList<Product> products = productsMap.get(productName);
        if (products == null || products.isEmpty()) {
            System.out.println("Продукт '" + productName + "' не найден в корзине");
            return;
        }

        System.out.println("Найдены продукты '" + productName + "':");


        IntStream.range(0, products.size())
                .forEach(i -> System.out.printf("%d. %s - %d руб.%n",
                        i + 1,
                        products.get(i).getDisplayInfo(),
                        products.get(i).getProductCost()));
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