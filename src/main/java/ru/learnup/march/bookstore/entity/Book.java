package ru.learnup.march.bookstore.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Книга
 */
@Entity
@Table
public class Book {

    /**
     * Идентификатор книги
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название
     */
    @Column
    private String title;

    /**
     * Автор
     */
    @JoinColumn
    @ManyToOne
    private Author author;

    /**
     * Год издания
     */
    @Column
    private int year;

    /**
     * Количество страниц
     */
    @Column
    private int pages;

    /**
     * Цена
     */
    @Column
    private double price;

    /**
     * Склад
     */
    @OneToOne
    @JoinColumn(name = "id")
    private BookStorage bookStorage;
}
