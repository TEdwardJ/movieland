package edu.eteslenko.movieland.entity;


public enum SortingColumn {
    RATING("m_rating"),
    PRICE("m_price"),
    DEFAULT("");

    private String sortingColumn;

    SortingColumn(String sortingColumn) {
        this.sortingColumn = sortingColumn;
    }

    public String getSortingColumn(){
        return sortingColumn;
    }


}
