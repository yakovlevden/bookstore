package ru.learnup.march.bookstore.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {

    private long id;
    private String firstName;
    private String patronymic;
    private String lastName;
}
