package ru.learnup.march.bookstore.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learnup.march.bookstore.entity.Author;

@Repository
public class AuthorDao {

    private static final String FIND_BY_ID = "select * from author where id = :id";
    private static final String SAVE =
            "insert into author (firstName, patronymic, lastName) " +
                    "values (:firstName, :patronymic, :lastName)";

    private final NamedParameterJdbcTemplate template;

    public AuthorDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void addAuthor(Author author) {
        template.update(SAVE, new MapSqlParameterSource()
                .addValue("firstName", author.getFirstName())
                .addValue("patronymic", author.getPatronymic())
                .addValue("lastName", author.getLastName())
        );
    }

    public Author findById(long id) {
        return template.query(
                FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                (rs, rn) -> Author.builder()
                        .id(rs.getLong("id"))
                        .firstName(rs.getString("firstName"))
                        .patronymic(rs.getString("patronymic"))
                        .lastName(rs.getString("lastName"))
                        .build()).stream().findAny().orElseThrow(() -> new RuntimeException("author with id = " + id + " is not found"));
    }
}
