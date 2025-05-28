package org.skypro.skyshop.article;

import java.util.LinkedList;
import java.util.List;

public class SearchEngine {
    private final LinkedList<Searchable> searchables;

    public SearchEngine() {
        this.searchables = new LinkedList<>();
    }

    public void addSearchable(Searchable searchable) {
        if (searchable == null) {
            System.out.println("Ошибка: попытка добавить null");
            return;
        }
        searchables.add(searchable);
    }

    public Searchable[] search(String query) {
        if (query == null || query.trim().isEmpty()) {
            System.out.println("Поисковый запрос не может быть пустым");
            return new Searchable[0];
        }

        List<Searchable> results = new LinkedList<>();
        String lowerQuery = query.toLowerCase();

        for (Searchable searchable : searchables) {
            if (searchable.getSearchTerm().toLowerCase().contains(lowerQuery)) {
                results.add(searchable);
                if (results.size() >= 5) {
                    break;
                }
            }
        }

        printSearchResults(query, results);
        return results.toArray(new Searchable[0]);
    }

    private void printSearchResults(String query, List<Searchable> results) {
        if (results.isEmpty()) {
            System.out.println("\nНичего не найдено по запросу: '" + query + "'");
            return;
        }

        System.out.println("\nРезультаты поиска ('" + query + "'):");
        System.out.println("----------------------------------");
        results.forEach(result ->
                System.out.println(result.getStringRepresentation()));
        System.out.println("----------------------------------");
        System.out.println("Найдено: " + results.size() + " из " + searchables.size());
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.trim().isEmpty()) {
            throw new BestResultNotFound("Пустой поисковый запрос");
        }

        Searchable bestMatch = null;
        String lowerQuery = query.toLowerCase();
        int bestScore = -1;

        for (Searchable searchable : searchables) {
            int currentScore = calculateMatchScore(searchable, lowerQuery);
            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestMatch = searchable;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(query);
        }

        System.out.println("\nНайден лучший результат по запросу '" + query + "':");
        System.out.println(bestMatch.getStringRepresentation());
        return bestMatch;
    }

    private int calculateMatchScore(Searchable searchable, String lowerQuery) {
        String searchTerm = searchable.getSearchTerm().toLowerCase();
        if (searchTerm.equals(lowerQuery)) return 100;
        if (searchTerm.startsWith(lowerQuery)) return 50;
        if (searchTerm.contains(lowerQuery)) return 30;
        return 0;
    }
}