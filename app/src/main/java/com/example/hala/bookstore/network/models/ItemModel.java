package com.example.hala.bookstore.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemModel implements Serializable {

    @SerializedName("image")
    @Expose
    private String image_book;

    @SerializedName("name")
    @Expose
    private String book_name;

    @SerializedName("price")
    @Expose
    private double book_price;

    @SerializedName("discount")
    @Expose
    private double discount = 0;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("rating")
    @Expose
    private double rating;

    @SerializedName("totalRating")
    @Expose
    private int totalRating;

    @SerializedName("isActive")
    @Expose
    private boolean isActive;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("bookDescription")
    @Expose
    private String bookDescription;

    @SerializedName("aboutAuthor")
    @Expose
    private String aboutAuthor;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("uptaded_at")
    @Expose
    private String uptaded_at;


    public ItemModel(String image_book, String book_name, int book_price) {
        this.image_book = image_book;
        this.book_name = book_name;
        this.book_price = book_price;
    }

    public String getImage_book() {
        return image_book;
    }

    public void setImage_book(String image_book) {
        this.image_book = image_book;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    public double getDiscount() { return discount; }

    public void setDiscount(double discount) { this.discount = discount; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public int getTotalRating() { return totalRating; }

    public void setTotalRating(int totalRating) { this.totalRating = totalRating; }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getBookDescription() { return bookDescription; }

    public void setBookDescription(String bookDescription) { this.bookDescription = bookDescription; }

    public String getAboutAuthor() { return aboutAuthor; }

    public void setAboutAuthor(String aboutAuthor) { this.aboutAuthor = aboutAuthor; }

    public String getCreated_at() { return created_at; }

    public void setCreated_at(String created_at) { this.created_at = created_at; }

    public String getUptaded_at() { return uptaded_at; }

    public void setUptaded_at(String uptaded_at) { this.uptaded_at = uptaded_at; }
}