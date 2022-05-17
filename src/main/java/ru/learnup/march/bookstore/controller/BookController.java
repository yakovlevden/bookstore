package ru.learnup.march.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.entity.BookStorage;
import ru.learnup.march.bookstore.services.BookService;
import ru.learnup.march.bookstore.services.BookStorageService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/list")
    public String home(Model model) {
        List<Book> booksList = bookService.getBooks();
        model.addAttribute("books", booksList);
        return "booksList";
    }
}
