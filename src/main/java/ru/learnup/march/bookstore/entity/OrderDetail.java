package ru.learnup.march.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Детали заказа
 */
@Entity
@Table
@Getter
@Setter
@Data
public class OrderDetail {

    /**
     * Идентификатор заказа
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Заказ
     */
    @OneToOne
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

    @Override
    public String toString() {
        return "OrderDetail{" +
                "ид=" + id +
                ", покупатель=" + customerOrder.getCustomer().getName() +
                ", сумма покупки=" + customerOrder.getSum() +
                ", книга=" + book +
                ", количество=" + amount +
                ", цена=" + price +
                '}';
    }
}
