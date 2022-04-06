package ru.learnup.march.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * Книжный склад
 */
@Entity
@Table
@Getter
@Setter
public class BookStorage {

    /**
     * Идентификатор книги
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Книга
     */
    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "book_id")
    private Book book;

    /**
     * Количество
     */
    @Column
    private Integer count;
}
