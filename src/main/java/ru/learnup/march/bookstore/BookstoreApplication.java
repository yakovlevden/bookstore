package ru.learnup.march.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.entity.CustomerOrder;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);
    }

}
