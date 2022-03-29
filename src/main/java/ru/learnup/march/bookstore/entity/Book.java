package ru.learnup.march.bookstore.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private long id;
    private String title;
    private Author author;
    private int year;
    private int pages;
    private double price;
}
