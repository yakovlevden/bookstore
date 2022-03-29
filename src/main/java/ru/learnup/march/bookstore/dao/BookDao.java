package ru.learnup.march.bookstore.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDao {

    AuthorDao authorDao;

    private static final String ADD_BOOK_QUERY =
            "insert into book (title, author_id, year, pages, price)" +
                    "values (:title, :author_id, :year, :pages, :price)";
    private static final String FIND_BY_ID = "select * from book where id = :id";
    private static final String GET_BOOKS_BY_TITLE_QUERY =
            "select * from book where title = :title";

    private final NamedParameterJdbcTemplate template;

    public BookDao(NamedParameterJdbcTemplate template, AuthorDao authorDao) {
        this.template = template;
        this.authorDao = authorDao;
    }

    public void addBook(Book book) {
        template.update(
                ADD_BOOK_QUERY,
                new MapSqlParameterSource()
                        .addValue("title", book.getTitle())
                        .addValue("author_id", book.getAuthor().getId())
                        .addValue("year", book.getYear())
                        .addValue("pages", book.getPages())
                        .addValue("price", book.getPrice()));
    }

    public Book findById(long id) {
        return template.query(
                FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                (rs, rn) -> getBookByResult(rs)).stream().findAny().orElseThrow(() -> new RuntimeException("book with id = " + id + " is not found"));
    }

    public List<Book> getBooksByTitle(String title) {
        return new ArrayList<>(template.query(
                GET_BOOKS_BY_TITLE_QUERY,
                new MapSqlParameterSource()
                        .addValue("title", title),
                (rs, rn) -> getBookByResult(rs)));
    }

    private Book getBookByResult(ResultSet rs) throws SQLException {
        return Book.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .author(authorDao.findById(rs.getLong("author_id")))
                .year(rs.getInt("year"))
                .pages(rs.getInt("pages"))
                .price(rs.getFloat("price"))
                .build();
    }
}
