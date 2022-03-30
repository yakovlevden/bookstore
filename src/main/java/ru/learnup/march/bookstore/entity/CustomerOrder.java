package ru.learnup.march.bookstore.entity;

import lombok.Data;
import org.hibernate.criterion.Order;

import javax.persistence.*;

/**
 * Заказ
 */
@Entity
@Table
public class CustomerOrder {

    /**
     * Идентификатор заказа
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Покупатель
     */
    @JoinColumn
    @ManyToOne
    private Customer customer;

    /**
     * Сумма покупки
     */
    @Column
    private double sum;

    /**
     * Детали заказа
     */
    @OneToOne
    @JoinColumn(name = "id")
    private OrderDetail orderDetail;
}
