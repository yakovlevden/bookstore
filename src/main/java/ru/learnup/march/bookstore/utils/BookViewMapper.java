package ru.learnup.march.bookstore.utils;

import org.springframework.stereotype.Component;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.view.AuthorView;
import ru.learnup.march.bookstore.view.BookView;

@Component
public class BookViewMapper {

    public BookView mapToView(Book book) {
        BookView view = new BookView();
        view.setId(book.getId());
        view.setTitle(book.getTitle());
        view.setPages(book.getPages());
        view.setYear(book.getYear());
        view.setPrice(book.getPrice());

        Author author = book.getAuthor();
        if (author != null) {
            view.setAuthor(new AuthorView(author.getId(), author.getName()));
        }
        return view;
    }

    public Book mapFromView(BookView view) {
        Book book = new Book();
        book.setId(view.getId());
        book.setTitle(view.getTitle());
        book.setPages(view.getPages());
        book.setYear(view.getYear());
        book.setPrice(view.getPrice());
        AuthorView authorView = view.getAuthor();
        if (authorView != null) {
            Author author = new Author(authorView.getName());
            author.setId(authorView.getId());
            book.setAuthor(author);
        }
        return book;
    }

}