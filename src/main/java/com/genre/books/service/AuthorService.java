package com.genre.books.service;
import com.genre.books.model.Author;
import com.genre.books.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public Page<Author> getAuthor(String author, Pageable page) {
        return repository.findByName(author, page);
    }

    public Page<Author> getAuthorName(String name, Pageable page) {
        return repository.findByNameStartingWith(name, page);
    }
}
