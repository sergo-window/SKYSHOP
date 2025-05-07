package org.skypro.skyshop;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.productbasket.ProductBasket;

public class App {

    public static void main(String[] args) {

        SimpleProduct simpleProduct1 = new SimpleProduct("Молоко", 80);
        SimpleProduct simpleProduct2 = new SimpleProduct("Хлеб", 70);
        DiscountedProduct discountedProduct1 = new DiscountedProduct("Колбаса", 180, 10);
        DiscountedProduct discountedProduct2 = new DiscountedProduct("Сахар", 60, 10);
        FixPriceProduct fixPriceProduct = new FixPriceProduct("Кофе");
        SimpleProduct simpleProduct6 = new SimpleProduct("Чай", 120);

        ProductBasket basket = new ProductBasket(5);
        System.out.println("=== Создана пустая корзина ===");

        System.out.println("\n=== Заполняем корзину ===");
        basket.addProduct(simpleProduct1);
        basket.addProduct(simpleProduct2);
        basket.addProduct(discountedProduct1);
        basket.addProduct(discountedProduct2);
        basket.addProduct(fixPriceProduct);

        System.out.println("\n=== Добавляем продукт в заполненную корзину ===");
        basket.addProduct(simpleProduct6);

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