/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.part5.pojo;

/**
 *
 * @author gaurav
 */

public class Book {
    private String isbn;
    private String title;
    private String authors;
    private Float price;

    public Book(String isbn, String title, String authors, Float price) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.price = price;
    }
    public Book() {
        }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
