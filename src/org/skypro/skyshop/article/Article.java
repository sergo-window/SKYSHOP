package org.skypro.skyshop.article;

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
}