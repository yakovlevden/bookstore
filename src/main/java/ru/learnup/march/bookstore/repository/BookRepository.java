package ru.learnup.march.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookByTitle(String title);

    @Query(value = "FROM Book b WHERE b.author.name LIKE %?1%")
    List<Book> findAllByAuthor(String name);
}
