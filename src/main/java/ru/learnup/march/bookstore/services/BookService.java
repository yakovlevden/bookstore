package ru.learnup.march.bookstore.services;

import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.repository.AuthorRepository;
import ru.learnup.march.bookstore.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book getBookByTitle(String title) {
        return repository.getBookByTitle(title);
    }
}
