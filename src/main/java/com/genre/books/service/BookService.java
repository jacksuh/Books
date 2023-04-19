package com.genre.books.service;

import com.genre.books.model.Book;
import com.genre.books.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BooksRepository repository;

    public List<Book> getAll() {
        return repository.findAll();
    }
}
