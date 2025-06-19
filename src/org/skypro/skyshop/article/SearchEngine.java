package org.skypro.skyshop.article;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> searchables;

    public SearchEngine() {
        this.searchables = new HashSet<>();
    }

    private static class SearchableComparator implements Comparator<Searchable> {
        @Override
        public int compare(Searchable o1, Searchable o2) {
            int lengthCompare = Integer.compare(
                    o2.getSearchableObjectName().length(),
                    o1.getSearchableObjectName().length()
            );
            if (lengthCompare == 0) {
                return o1.getSearchableObjectName().compareTo(o2.getSearchableObjectName());
            }
            return lengthCompare;
        }
    }

    public void addSearchable(Searchable searchable) {
        if (searchable == null) {
            System.out.println("Ошибка: попытка добавить null");
            return;
        }
        if (!searchables.add(searchable)) {
            System.out.println("\nОбъект '" + searchable.getSearchableObjectName() + "' уже существует");
        }
    }

    public TreeSet<Searchable> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            System.out.println("Поисковый запрос не может быть пустым");
            return new TreeSet<>(new SearchableComparator());
        }

        String lowerQuery = query.toLowerCase();

        TreeSet<Searchable> results = searchables.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.getSearchTerm().toLowerCase().contains(lowerQuery))
                .limit(5)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(new SearchableComparator())
                ));

        printSearchResults(query, results);
        return results;
    }

    private void printSearchResults(String query, Set<Searchable> results) {
        if (results.isEmpty()) {
            System.out.println("\nНичего не найдено по запросу: '" + query + "'");
            return;
        }

        System.out.println("\nРезультаты поиска ('" + query + "'):");
        System.out.println("----------------------------------");
        results.forEach(searchable ->
                System.out.println(searchable.getStringRepresentation()));
        System.out.println("----------------------------------");
        System.out.println("Найдено: " + results.size() + " из " + searchables.size());
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.trim().isEmpty()) {
            throw new BestResultNotFound("Пустой поисковый запрос");
        }

        return search(query).stream()
                .max((s1, s2) -> {
                    String lowerQuery = query.toLowerCase();
                    int score1 = calculateMatchScore(s1, lowerQuery);
                    int score2 = calculateMatchScore(s2, lowerQuery);
                    return Integer.compare(score1, score2);
                })
                .orElseThrow(() -> new BestResultNotFound(query));
    }

    private int calculateMatchScore(Searchable searchable, String lowerQuery) {
        String searchTerm = searchable.getSearchTerm().toLowerCase();
        if (searchTerm.equals(lowerQuery)) return 100;
        if (searchTerm.startsWith(lowerQuery)) return 50;
        if (searchTerm.contains(lowerQuery)) return 30;
        return 0;
    }
}