package ru.learnup.march.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import javax.persistence.*;

/**
 * Заказ
 */
@Entity
@Table
@Getter
@Setter
public class CustomerOrder {

    /**
     * Идентификатор заказа
     */
    @Id
    @Column(name = "id")
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
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "order_detail_id")
    private OrderDetail orderDetail;
}
