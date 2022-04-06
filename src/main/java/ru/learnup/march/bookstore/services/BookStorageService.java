package ru.learnup.march.bookstore.services;

import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.entity.BookStorage;
import ru.learnup.march.bookstore.repository.BookRepository;
import ru.learnup.march.bookstore.repository.BookStorageRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookStorageService {

    private final BookStorageRepository repository;

    public BookStorageService(BookStorageRepository repository) {
        this.repository = repository;
    }

    public BookStorage createBookStorage(Book book, Integer count) {
        BookStorage bookStorage = new BookStorage();
        bookStorage.setId(book.getId());
        bookStorage.setCount(5);
        return repository.save(bookStorage);
    }

    public Integer getCountByBook(Book book) {
        Integer count = repository.getCountById(book.getId());
        return count != null ? count : 0;
    }

    @Transactional
    public void takeBook(Book book, Integer number) {
        repository.takeBook(book.getId(), number);
    }
}
