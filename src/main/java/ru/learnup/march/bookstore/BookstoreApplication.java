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
import java.util.Scanner;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);

        AuthorService authorService = context.getBean(AuthorService.class);
        BookService bookService = context.getBean(BookService.class);
        BookStorageService bookStorageService = context.getBean(BookStorageService.class);
        CustomerService customerService = context.getBean(CustomerService.class);
        CustomerOrderService customerOrderService = context.getBean(CustomerOrderService.class);
        OrderDetailService orderDetailService = context.getBean(OrderDetailService.class);

        Book book = bookService.getBookByTitle("Капитанская дочка");
        if (book == null) {
            Author author = authorService.getAuthorByName("Александр Сергеевич Пушкин");
            if (author == null) {
                author = new Author();
                author.setName("Александр Сергеевич Пушкин");
                author = authorService.createAuthor(author);
            }

            book = new Book();
            book.setAuthor(author);
            book.setYear(1732);
            book.setTitle("Капитанская дочка");
            book.setPages(123);
            book.setPrice(654.50);

            bookService.createBook(book);

            bookStorageService.createBookStorage(book, 5);
        }

        Integer number = 2;

        Integer count = bookStorageService.getCountByBook(book);
        if (count >= number) {

            Customer customer = customerService.getCustomerByName("Александр Иванович Смирнов");
            if (customer == null) {
                customer = new Customer();
                customer.setName("Александр Иванович Смирнов");
                customer.setBirthdate(Date.from(LocalDate.of(1978, 4, 6).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                customerService.createCustomer(customer);
            }

            List<CustomerOrder> ordersByCustomer = customerOrderService.getCustomerOrdersByCustomer(customer);
            Book finalBook = book;
            if (ordersByCustomer.stream().noneMatch(co -> co.getOrderDetail().getBook().equals(finalBook))) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setBook(book);
                orderDetail.setPrice(book.getPrice());
                orderDetail.setAmount(number);
                orderDetailService.createOrderDetail(orderDetail);

                customerOrderService.createCustomerOrder(customer, orderDetail);
            }

            bookStorageService.takeBook(book, number);
        }

        List<OrderDetail> orderDetailsList = orderDetailService.getOrderDetails();
        for (OrderDetail od : orderDetailsList) {
            log.info("{}", od);
        }
    }

}
