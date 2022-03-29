package ru.learnup.march.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.march.bookstore.dao.AuthorDao;
import ru.learnup.march.bookstore.dao.BookDao;
import ru.learnup.march.bookstore.dao.OrderDao;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.entity.Order;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);

        AuthorDao authorDao = context.getBean(AuthorDao.class);
        BookDao bookDao = context.getBean(BookDao.class);
        OrderDao orderDao = context.getBean(OrderDao.class);

        Author author = authorDao.findById(1);
        log.info("{}", author);

//		Book newBook = Book.builder()
//				.author(author)
//				.title("Капитанская дочка")
//				.pages(213)
//				.year(1723)
//				.price(123.50)
//				.build();
//		bookDao.addBook(newBook);

        System.out.println("Какую книгу хотите купить?");
        Scanner in = new Scanner(System.in);
        String title = in.next();
        List<Book> booksByTitle = bookDao.getBooksByTitle(title);

        if (booksByTitle.isEmpty()) {
            System.out.printf("В магазине нет книг с названием '%s'%n", title);
        } else {
            for (Book book : booksByTitle) {
                System.out.printf("Найдена книга автор: %s, название: %s, цена: %.2f",
                        book.getAuthor().getLastName(),
                        book.getTitle(),
                        book.getPrice());
                System.out.println();
                System.out.println("Введите количество?");
                int amount = in.nextInt();
                orderDao.buyBook(book, amount);
            }
        }

        System.out.println("Чек:");
        List<Order> orders = orderDao.getAllOrders();
        for (Order order : orders) {
            System.out.printf("%s '%s' количество %d цена %.2f%n", order.getBook().getAuthor().getLastName(), order.getBook().getTitle(), order.getAmount(), order.getCost());
        }

    }

}
