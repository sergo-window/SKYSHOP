package org.skypro.skyshop.article;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final Searchable[] searchables;
    private int count;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.count = 0;
    }

    public void addSearchable(Searchable searchable) {
        if (searchable == null) {
            System.out.println("Ошибка: попытка добавить null");
            return;
        }
        if (count < searchables.length) {
            searchables[count] = searchable;
            count++;
        } else {
            System.out.println("Достигнут максимум поисковых объектов!");
        }
    }

    public void search(String query) {
        if (query == null || query.trim().isEmpty()) {
            System.out.println("Поисковый запрос не может быть пустым");
            return;
        }

        List<Searchable> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (int i = 0; i < count; i++) {
            Searchable searchable = searchables[i];
            if (searchable.getSearchTerm().toLowerCase().contains(lowerQuery)) {
                results.add(searchables[i]);
                if (results.size() >= 5) {
                    break;
                }
            }
        }

        if (results.isEmpty()) {
            System.out.println("\nНичего не найдено по запросу: '" + query + "'");
            return;
        }

        System.out.println("\nРезультаты поиска ('" + query + "'):");
        System.out.println("----------------------------------");
        for (Searchable result : results) {
            System.out.println(result.getStringRepresentation());
        }
        System.out.println("----------------------------------");
        System.out.println("Найдено: " + results.size() + " из " + count);
    }
}
