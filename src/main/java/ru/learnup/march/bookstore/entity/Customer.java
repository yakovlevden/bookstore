package ru.learnup.march.bookstore.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;

/**
 * Покупатель
 */
@Entity
@Table
@Getter
@Setter
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
