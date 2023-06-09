package com.genre.books.controller;
import com.genre.books.dto.BookDetailingDto;
import com.genre.books.dto.BookDto;
import com.genre.books.model.Author;
import com.genre.books.model.Book;
import com.genre.books.service.AuthorService;
import com.genre.books.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = "book", allEntries = true)
    public ResponseEntity books(@RequestBody @Valid BookDto dto, UriComponentsBuilder uriBuilder){
        Book b = service.saveBook(dto);
        var uri = uriBuilder.path("/book/{id}").buildAndExpand(b.getAuthor()).toUri();
        return ResponseEntity.created(uri).body(new BookDetailingDto((Book) b));
    }

    @GetMapping
    @Cacheable(value = "book")
    public ResponseEntity <Page<Book>> getBooks(Pageable page){
        Page<Book> list = service.getAll(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "titulo")
    @CacheEvict(value = "book", allEntries = true)
    public ResponseEntity <Page<Book>> getBooksTitle(@RequestParam(name = "titulo") String titulo, Pageable page){
        Page<Book> list = service.getTitle(titulo, page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "genero")
    @CacheEvict(value = "book", allEntries = true)
    public ResponseEntity <Page<Book>> getGenre(@RequestParam(name = "genero") String genero, Pageable page){
        Page<Book> list = service.getGenero(genero, page);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "author")
    @CacheEvict(value = "book", allEntries = true)
    public ResponseEntity <Page<Author>> getAuthor(@RequestParam(name = "author") String author, Pageable page){
        Page<Author> list = authorService.getAuthor(author, page);
        return ResponseEntity.ok(list);
    }


    @GetMapping(value = "name")
    @CacheEvict(value = "book", allEntries = true)
    public ResponseEntity <Page<Author>> getAuthorName(@RequestParam(name = "name") String name, Pageable page){
        Page<Author> list = authorService.getAuthorName(name, page);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "book", allEntries = true)
    public ResponseEntity deleteBook(@PathVariable Long id){
        service.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}
