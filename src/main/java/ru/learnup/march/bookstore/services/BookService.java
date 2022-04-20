package ru.learnup.march.bookstore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.repository.AuthorRepository;
import ru.learnup.march.bookstore.repository.BookRepository;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Book getById(Long id) {
        return repository.getById(id);
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book getBookByTitle(String title) {
        return repository.getBookByTitle(title);
    }

    @Transactional
    @Lock(value = LockModeType.READ)
    public Book update(Book book) {
        try {
            return repository.save(book);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for book {}", book.getId());
            throw e;
        }
    }

    public List<Book> findAllByAuthor(String authorName) {
        return repository.findAllByAuthor(authorName);
    }

    public Boolean delete(Long id) {
        repository.delete(repository.getById(id));
        return true;
    }
}
