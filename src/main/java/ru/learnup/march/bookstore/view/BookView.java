package ru.learnup.march.bookstore.view;

import lombok.Data;

@Data
public class BookView {

    private Long id;

    private String title;

    private Integer year;

    private Integer pages;

    private Double price;

    private AuthorView author;
}
