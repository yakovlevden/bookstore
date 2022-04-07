package ru.learnup.march.bookstore.services;

import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author createAuthor(Author author) {
        return repository.save(author);
    }

    public List<Author> getAuthors() {
        return repository.findAll();
    }

    public Author getAuthorById(Long id) {
        return repository.getById(id);
    }

    public Author getAuthorByName(String name) {
        return repository.getAuthorByName(name);
    }
}
