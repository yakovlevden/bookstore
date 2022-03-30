package ru.learnup.march.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Детали заказа
 */
@Entity
@Table
public class OrderDetail {

    /**
     * Идентификатор заказа
     */
    @Id
    private Long id;

    /**
     * Заказ
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private CustomerOrder customerOrder;

    /**
     * Книга
     */
    @JoinColumn
    @ManyToOne
    private Book book;

    /**
     * Количество
     */
    @Column
    private int amount;

    /**
     * Цена
     */
    @Column
    private double price;

}
