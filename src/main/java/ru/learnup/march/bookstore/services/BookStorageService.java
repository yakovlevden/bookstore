package ru.learnup.march.bookstore.services;

import org.hibernate.annotations.OptimisticLock;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.entity.BookStorage;
import ru.learnup.march.bookstore.repository.BookRepository;
import ru.learnup.march.bookstore.repository.BookStorageRepository;

import javax.persistence.LockModeType;
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
        bookStorage.setCount(count);
        return repository.save(bookStorage);
    }

    public Integer getCountByBook(Book book) {
        Integer count = repository.getCountById(book.getId());
        return count != null ? count : 0;
    }

    @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public void takeBook(Book book, Integer number) throws Exception {
        int result = repository.takeBook(book.getId(), number);
        if (result == 0) {
            throw new Exception(String.format("На складе нет книги %s в количестве %d штук.", book, number));
        }
    }
}
