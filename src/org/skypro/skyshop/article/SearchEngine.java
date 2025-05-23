package org.skypro.skyshop.article;

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

    public Searchable[] search(String query) {
        if (query == null || query.trim().isEmpty()) {
            System.out.println("Поисковый запрос не может быть пустым");
            return new Searchable[0];
        }

        Searchable[] results = new Searchable[5];
        int foundCount = 0;
        String lowerQuery = query.toLowerCase();

        for (int i = 0; i < count && foundCount < 5; i++) {
            Searchable searchable = searchables[i];
            if (searchable.getSearchTerm().toLowerCase().contains(lowerQuery)) {
                results[foundCount] = searchable;
                foundCount++;
            }
        }

        printSearchResults(query, results, foundCount);
        return trimResultsArray(results, foundCount);
    }

    private void printSearchResults(String query, Searchable[] results, int foundCount) {
        if (foundCount == 0) {
            System.out.println("\nНичего не найдено по запросу: '" + query + "'");
            return;
        }

        System.out.println("\nРезультаты поиска ('" + query + "'):");
        System.out.println("----------------------------------");
        for (int i = 0; i < foundCount; i++) {
            System.out.println(results[i].getStringRepresentation());
        }
        System.out.println("----------------------------------");
        System.out.println("Найдено: " + foundCount + " из " + count);
    }

    private Searchable[] trimResultsArray(Searchable[] results, int actualSize) {
        if (actualSize == results.length) {
            return results;
        }

        Searchable[] trimmedArray = new Searchable[actualSize];
        System.arraycopy(results, 0, trimmedArray, 0, actualSize);
        return trimmedArray;
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.trim().isEmpty()) {
            throw new BestResultNotFound("Пустой поисковый запрос");
        }

        Searchable bestMatch = null;
        String lowerQuery = query.toLowerCase();
        int bestScore = -1;

        for (int i = 0; i < count; i++) {
            Searchable searchable = searchables[i];
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
        if (searchTerm.equals(lowerQuery)) {
            return 100;
        }
        if (searchTerm.startsWith(lowerQuery)) {
            return 50;
        }
        if (searchTerm.contains(lowerQuery)) {
            return 30;
        }
        return 0;
    }
}