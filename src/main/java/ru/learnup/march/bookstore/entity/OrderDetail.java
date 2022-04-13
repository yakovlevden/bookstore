package ru.learnup.march.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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
                ", книга=" + book +
                ", количество=" + amount +
                ", цена=" + price +
                '}';
    }

    public OrderDetail(Book book, int amount, double price) {
        this.book = book;
        this.amount = amount;
        this.price = price;
    }
}
