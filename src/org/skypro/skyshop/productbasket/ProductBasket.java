package org.skypro.skyshop.productbasket;

import org.skypro.skyshop.product.Product;

import java.util.Iterator;
import java.util.LinkedList;


public class ProductBasket {
    private final LinkedList<Product> products;

    public ProductBasket() {
        this.products = new LinkedList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Ошибка: попытка добавить null");
            return;
        }
        products.add(product);
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        int specialCount = 0;
        for (Product product : products) {
            System.out.println(product.getDisplayInfo());
            if (product.isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("ИТОГО: " + getTotalCost() + " руб.");
        System.out.println("Специальных товаров: " + specialCount);
    }

    public int getTotalCost() {
        int total = 0;
        for (Product product : products) {
            total += product.getProductCost();
        }
        return total;
    }

    public void searchAndPrintProduct(String productName) {
        boolean found = false;
        int position = 1;
        for (Product product : products) {
            if (product.getProductName().equals(productName)) {
                System.out.printf("Найден продукт: %s - %d руб. (позиция %d)%n",
                        productName,
                        product.getProductCost(),
                        position);
                found = true;
            }
            position++;
        }
        if (!found) {
            System.out.println("Продукт '" + productName + "' не найден в корзине");
        }
    }

    public void clearBasket() {
        products.clear();
        System.out.println("Корзина очищена");
    }

    public LinkedList<Product> removeProduct(String name) {
        LinkedList<Product> removedProducts = new LinkedList<>();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product != null && product.getProductName().equalsIgnoreCase(name)) {
                removedProducts.add(product);
                iterator.remove();
            }
        }
        if (removedProducts.isEmpty()) {
            System.out.println("Список пуст");
        }
        return removedProducts;
    }
}