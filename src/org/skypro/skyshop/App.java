package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.article.BestResultNotFound;
import org.skypro.skyshop.article.SearchEngine;
import org.skypro.skyshop.article.Searchable;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.productbasket.ProductBasket;

import java.util.List;
import java.util.TreeMap;


public class App {

    public static void main(String[] args) {

        SearchEngine engine = new SearchEngine();

        try {

            engine.addSearchable(new SimpleProduct("Молоко", 80));
            engine.addSearchable(new SimpleProduct("Хлеб", 70));
            engine.addSearchable(new DiscountedProduct("   ", 180, 10));
            engine.addSearchable(new DiscountedProduct("Сахар", 60, 10));
            engine.addSearchable(new FixPriceProduct("Кофе"));
            engine.addSearchable(new SimpleProduct("Чай", 120));
            engine.addSearchable(new Article("Польза молока", "Молоко содержит кальций..."));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        engine.addSearchable(new Article("Кому полезно молоко", "Существует много мнений о пользе или вреде молочных продуктов..."));
        engine.addSearchable(new Article("Как выбрать чай", "Чай бывает разных сортов (черный, зеленый, красный..."));
        engine.addSearchable(new Article("Из чего делают сахар", "Для изготовления сахара применяются различные технологии..."));

        engine.search("молоко");
        engine.search("сахар");
        engine.search("чай");
        engine.search("шоколад");

        TreeMap<String, Searchable> results = engine.search("молоко");

        System.out.println("\nДетализация результатов:");
        results.forEach((name, obj) -> {
            System.out.println("Ключ: " + name);
            System.out.println("Тип: " + obj.getSearchableObjectType());
        });

        System.out.println("\nТолько продукты:");
        for (Searchable s : results.values()) {
            if (s.getSearchableObjectType().equals("PRODUCT")) {
                System.out.println(s);
            }
        }

        try {

            Searchable bestMatch = engine.findBestMatch("польза");
            System.out.println("\nЛучший результат: " + bestMatch.getSearchableObjectName());

        } catch (BestResultNotFound e) {
            System.out.println("\nОШИБКА! " + e.getMessage());
        }

        ProductBasket basket = new ProductBasket();

        System.out.println("\n=== Создана пустая корзина ===");

        System.out.println("\n=== Заполняем корзину ===");

        basket.addProduct(new SimpleProduct("Молоко", 80));
        basket.addProduct(new SimpleProduct("Хлеб", 70));
        basket.addProduct(new DiscountedProduct("Колбаса", 180, 10));
        basket.addProduct(new DiscountedProduct("Сахар", 60, 10));
        basket.addProduct(new FixPriceProduct("Кофе"));
        basket.addProduct(new SimpleProduct("Молоко", 90));

        basket.printBasket();

        System.out.println("\n=== Удаляем продукт 'Молоко' ===");
        List<Product> removed = basket.removeProduct("Молоко");

        System.out.println("\nУдаленные продукты:");
        if (!removed.isEmpty()) {
            removed.forEach(product ->
                    System.out.println(product.getProductName() + " - " + product.getProductCost() + " руб."));
        }

        System.out.println("\nСодержимое корзины после удаления:");
        basket.printBasket();

        System.out.println("\n=== Пытаемся удалить несуществующий продукт 'Шоколад' ===");
        List<Product> notFound = basket.removeProduct("Шоколад");

        if (notFound.isEmpty()) {
            System.out.println("\nСписок удаленных продуктов пуст (продукт не найден)");
        }

        System.out.println("\nСодержимое корзины после попытки удаления несуществующего продукта:");
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