package ru.learnup.march.bookstore.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * Книжный склад
 */
@Entity
@Table
public class BookStorage {

    /**
     * Идентификатор книги
     */
    @Id
    private Long id;

    /**
     * Книга
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Book book;

    /**
     * Количество
     */
    @Column
    private Integer count;
}
