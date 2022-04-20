package ru.learnup.march.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.march.bookstore.entity.*;
import ru.learnup.march.bookstore.repository.BookStorageRepository;
import ru.learnup.march.bookstore.services.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    static AuthorService authorService;
    static BookService bookService;
    static BookStorageService bookStorageService;
    static CustomerService customerService;
    static CustomerOrderService customerOrderService;
    static OrderDetailService orderDetailService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);

        authorService = context.getBean(AuthorService.class);
        bookService = context.getBean(BookService.class);
        bookStorageService = context.getBean(BookStorageService.class);
        customerService = context.getBean(CustomerService.class);
        customerOrderService = context.getBean(CustomerOrderService.class);
        orderDetailService = context.getBean(OrderDetailService.class);

//        initializeData();

        List<Book> allBooks = bookService.getBooks();
        List<Customer> allCustomers = customerService.getCustomers();

        Thread thread1 = new Thread(() -> {
            Customer customer = allCustomers.get(0);
            buyBook(customer, allBooks);
        });
        Thread thread2 = new Thread(() -> {
            Customer customer = allCustomers.get(1);
            buyBook(customer, allBooks);
        });
        Thread thread3 = new Thread(() -> {
            Customer customer = allCustomers.get(2);
            buyBook(customer, allBooks);
        });
        Thread thread4 = new Thread(() -> {
            Customer customer = allCustomers.get(3);
            buyBook(customer, allBooks);
        });

//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
    }

    private static void buyBook(Customer customer, List<Book> allBooks) {
        for (int i = 0; i < 50; i++) {
            int amount = new Random().nextInt(5) + 1;
            Book book = allBooks.get(new Random().nextInt(allBooks.size()));
            try {
                OrderDetail orderDetail = orderDetailService.buyBook(customer, book, amount);
                log.info("Заказ успешно выполнен: {} для {} в количестве {} штук.",
                        book,
                        customer.getName(),
                        amount);
            } catch (Exception e) {
                log.info("Не удалось выполнить заказ: {}", e.getMessage());
            }
        }
    }

    public static void initializeData() {
        Author author1 = new Author("Александр Сергеевич Пушкин");
        author1 = authorService.createAuthor(author1);

        Book book11 = new Book("Капитанская дочка", author1, 1836, 150, 300);
        book11 = bookService.createBook(book11);
        bookStorageService.createBookStorage(book11, 15);

        Book book12 = new Book("Дубровский", author1, 1833, 100, 200);
        book12 = bookService.createBook(book12);
        bookStorageService.createBookStorage(book12, 25);

        Book book13 = new Book("Евгений Онегин", author1, 1832, 150, 300);
        book13 = bookService.createBook(book13);
        bookStorageService.createBookStorage(book13, 5);


        Author author2 = new Author("Лев Николаевич Толстой");
        author2 = authorService.createAuthor(author2);

        Book book21 = new Book("Война и мир", author2, 1850, 500, 1200);
        book21 = bookService.createBook(book21);
        bookStorageService.createBookStorage(book21, 10);

        Book book22 = new Book("Анна Каренина", author2, 1870, 400, 300);
        book22 = bookService.createBook(book22);
        bookStorageService.createBookStorage(book22, 20);


        Customer customer1 = new Customer("Смирнов Александр Степанович");
        customerService.createCustomer(customer1);

        Customer customer2 = new Customer("Иванов Сергей Фёдорович");
        customerService.createCustomer(customer2);

        Customer customer3 = new Customer("Петров Николай Максимович");
        customerService.createCustomer(customer3);

        Customer customer4 = new Customer("Алексеев Артём Владимирович");
        customerService.createCustomer(customer4);
    }
}
