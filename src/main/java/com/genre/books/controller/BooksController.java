package com.genre.books.controller;
import com.genre.books.dto.BookDetailingDto;
import com.genre.books.dto.BookDto;
import com.genre.books.model.Author;
import com.genre.books.model.Book;
import com.genre.books.repository.BooksRepository;
import com.genre.books.service.AuthorService;
import com.genre.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("livros")
public class BooksController {

    @Autowired
    private BookService service;

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity livros(@RequestBody BookDto dto, UriComponentsBuilder uriBuilder){
        Book b = service.saveBook(dto);
        var uri = uriBuilder.path("/book/{id}").buildAndExpand(b.getAuthor()).toUri();
        return ResponseEntity.created(uri).body(new BookDetailingDto((Book) b));
    }

    @GetMapping
    public ResponseEntity <Page<Book>> getLivros(Pageable page){
        Page<Book> list = service.getAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "titulo")
    public ResponseEntity <Page<Book>> getBooksTitle(@RequestParam(name = "titulo") String titulo, Pageable page){
        Page<Book> list = service.getTitle(titulo, page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "genero")
    public ResponseEntity <Page<Book>> getGenre(@RequestParam(name = "genero") String genero, Pageable page){
        Page<Book> list = service.getGenero(genero, page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "author")
    public ResponseEntity <Page<Author>> getAuthor(@RequestParam(name = "author") String author, Pageable page){
        Page<Author> list = authorService.getAuthor(author, page);
        return ResponseEntity.ok(list);
    }



}
