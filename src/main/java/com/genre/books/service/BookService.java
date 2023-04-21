package com.genre.books.service;
import com.genre.books.dto.BookDto;
import com.genre.books.model.Book;
import com.genre.books.repository.AuthorRepository;
import com.genre.books.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BooksRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    public Page<Book> getAll(Pageable page) {
        return repository.findAll(page);
    }



    public Book saveBook(BookDto dto) {
        List<Book> books1 = new ArrayList<Book>();
        Book b = new Book();
        b.setAuthor(dto.author());
        b.setGenre(dto.genre());
        b.setTitle(dto.title());

        books1.add(b);

        repository.saveAll(books1);

        return b;
    }


    public Page<Book> getTitle(String title, Pageable page) {
        return repository.findByTitle(title, page);
    }

    public Page<Book> getGenero(String genre, Pageable page) {
        return repository.findByGenre(genre, page);
    }




}
