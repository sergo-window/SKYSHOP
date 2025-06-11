package org.skypro.skyshop.article;

import java.util.Objects;

public class Article implements Searchable {
    private final String articleName;
    private final String articleText;

    public Article(String articleName, String articleText) {
        this.articleName = articleName;
        this.articleText = articleText;
    }

    @Override
    public String getSearchTerm() {
        return articleName + " " + articleText;
    }

    @Override
    public String getSearchableObjectType() {
        return "ARTICLE";
    }

    @Override
    public String getSearchableObjectName() {
        return articleName;
    }

    @Override
    public String toString() {
        return "Статья: '" + articleName + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(getSearchableObjectName(), ((Searchable) o).getSearchableObjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSearchableObjectName());
    }
}