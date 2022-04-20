package ru.learnup.march.bookstore.controller;

import org.springframework.web.bind.annotation.*;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.services.BookService;
import ru.learnup.march.bookstore.utils.BookViewMapper;
import ru.learnup.march.bookstore.view.BookView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/rest/books")
public class BookRest {

    private final BookService bookService;
    private final BookViewMapper mapper;

    public BookRest(BookService bookService, BookViewMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<BookView> getAllBooks() {
        return bookService.getBooks()
                .stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("byAuthor")
    public List<BookView> getBooks(
            @RequestParam(value = "author", required = false) String author
    ) {
        return bookService.findAllByAuthor(author)
                .stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/{bookId}")
    public BookView getBook(@PathVariable("bookId") Long id) {
        return mapper.mapToView(bookService.getById(id));
    }

    @PostMapping
    public BookView createBook(@RequestBody BookView view) {
        if (view.getId() != null) {
            throw new EntityExistsException(
                    String.format("Книга с идетификатором %s уже создана", view.getId())
            );
        }
        Book book = mapper.mapFromView(view);
        Book createdBook = bookService.createBook(book);
        return mapper.mapToView(createdBook);
    }

    @PutMapping("/{bookId}")
    public BookView updateBook(
            @PathVariable("bookId") Long id,
            @RequestBody BookView view
    ) {
        if (view.getId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(id, view.getId())) {
            throw new RuntimeException("Entity has bad id");
        }

        Book book = bookService.getById(id);
        book.setTitle(view.getTitle());
        book.setPages(view.getPages());
        book.setYear(view.getYear());
        book.setPrice(view.getPrice());

        Book updated = bookService.update(book);

        return mapper.mapToView(updated);

    }

    @DeleteMapping("/{bookId}")
    public Boolean deleteBook(@PathVariable("bookId") Long bookId) {
        return bookService.delete(bookId);
    }

}