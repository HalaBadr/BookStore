package com.example.hala.bookstore.network.models;

import java.io.Serializable;
import java.util.List;

public class MyBooks implements Serializable {

    private List<ItemModel> books;

    public List<ItemModel> getBooks() {
        return books;
    }

    public void setBooks(List<ItemModel> books) {
        this.books = books;
    }
}