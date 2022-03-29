package ru.learnup.march.bookstore.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private long id;
    private Book book;
    private int amount;
    private double cost;
}
