package org.skypro.skyshop.article;

public interface Searchable {
    String getSearchTerm();

    String getSearchableObjectType();

    default String getStringRepresentation() {
        return getSearchableObjectName() + " [" + getSearchableObjectType() + "]";
    }

    String getSearchableObjectName();
}