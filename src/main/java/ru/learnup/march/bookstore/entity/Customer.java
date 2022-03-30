package ru.learnup.march.bookstore.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Покупатель
 */
@Entity
@Table
public class Customer {

    /**
     * Идентификатор покупателя
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
     * Дата рождения
     */
    @Column
    @Temporal(TemporalType.DATE)
    private Date birthdate;
}
