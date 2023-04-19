package com.genre.books.controller;


import com.genre.books.dto.BooksDto;
import com.genre.books.model.Author;
import com.genre.books.model.Book;
import com.genre.books.repository.BooksRepository;
import com.genre.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("livros")
public class BooksController {

    @Autowired
    private BooksRepository repository;

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity livros(@RequestBody BooksDto dto){
        List<Book> books1 = new ArrayList<Book>();

        Book n = new Book();

        n.setTitulo(dto.getTitulo());
        n.setGenero(dto.getGenero());
        n.setAuthor(dto.getAuthor());

        books1.add(n);

        repository.saveAll(books1);

        return ResponseEntity.ok(books1);
    }

    @GetMapping
    public ResponseEntity <List<Book>> getLivros(){
        List<Book> list = service.getAll();
        return ResponseEntity.ok(list);
    }
}
