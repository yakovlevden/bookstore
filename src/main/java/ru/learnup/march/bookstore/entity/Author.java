package ru.learnup.march.bookstore.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * Автор
 */
@Entity
@Table
public class Author {

    /**
     * Идентификатор автора
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ФИО
     */
    @Column
    private String name;

    /**
     * Книги
     */
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Book> books;

}
