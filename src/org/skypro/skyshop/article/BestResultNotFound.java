package org.skypro.skyshop.article;


public class BestResultNotFound extends Exception {
    public BestResultNotFound(String query) {
        super("По запросу '" + query + "' ничего не найдено");
    }

    public BestResultNotFound() {
        super("Результат не найден");
    }
}
