package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.productbasket.ProductBasket;

public class App {

    public static void main(String[] args) {

        Product product1 = new Product("Молоко", 80);
        Product product2 = new Product("Хлеб", 70);
        Product product3 = new Product("Колбаса", 180);
        Product product4 = new Product("Сахар", 60);
        Product product5 = new Product("Соль", 50);
        Product product6 = new Product("Чай", 120);

        ProductBasket basket = new ProductBasket(5);
        System.out.println("=== Создана пустая корзина ===");

        System.out.println("\n=== Заполняем корзину ===");
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        System.out.println("\n=== Добавляем продукт в заполненную корзину ===");
        basket.addProduct(product6);

        System.out.println("\n=== Содержимое корзины ===");
        basket.printBasket();

        System.out.println("\n=== Поиск существующего товара ===");
        basket.searchAndPrintProduct("Сахар");

        System.out.println("\n=== Поиск отсутствующего товара ===");
        basket.searchAndPrintProduct("Чай");

        System.out.println("\n=== Очистка корзины ===");
        basket.clearBasket();

        System.out.println("\n=== Содержимое пустой корзины ===");
        basket.printBasket();

        System.out.println("\n=== Стоимость пустой корзины ===");
        System.out.println("Общая стоимость: " + basket.getTotalCost() + " руб.");

        System.out.println("\n=== Поиск товара в пустой корзине ===");
        basket.searchAndPrintProduct("Молоко");
    }
}