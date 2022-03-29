package ru.learnup.march.bookstore.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDao {

    BookDao bookDao;

    private static final String ADD_ORDER_QUERY =
            "insert into \"order\" (book_id, amount, cost)" +
                    "values (:book_id, :amount, :cost)";
    private static final String GET_ALL_ORDERS_QUERY =
            "select * from \"order\"";

    private final NamedParameterJdbcTemplate template;

    public OrderDao(NamedParameterJdbcTemplate template, BookDao bookDao) {
        this.template = template;
        this.bookDao = bookDao;
    }

    public void buyBook(Book book, int amount) {
        if (amount == 0) {
            return;
        }
        template.update(
                ADD_ORDER_QUERY,
                new MapSqlParameterSource()
                        .addValue("book_id", book.getId())
                        .addValue("amount", amount)
                        .addValue("cost", book.getPrice()));
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(template.query(
                GET_ALL_ORDERS_QUERY,
                new MapSqlParameterSource(),
                (rs, rn) -> Order.builder()
                        .id(rs.getLong("id"))
                        .book(bookDao.findById(rs.getLong("book_id")))
                        .cost(rs.getDouble("cost"))
                        .amount(rs.getInt("amount"))
                        .build()));
    }
}
