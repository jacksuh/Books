package com.genre.books.service;

import com.genre.books.dto.BookDto;
import com.genre.books.exception.ValidationException;
import com.genre.books.model.Book;
import com.genre.books.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BooksRepository repository;

    public Page<Book> getAll(Pageable page) {
        return repository.findAll(page);
    }
    public Book saveBook(BookDto dto) {
        Optional<Book> book = Optional.ofNullable(repository.findByTitle(dto.title()));

        if (book.isPresent()) {
            throw new ValidationException("Book already exists!");
        } else {

            Book b = new Book();
            b.setAuthor(dto.author());
            b.setGenre(dto.genre());
            b.setTitle(dto.title());

            Book savedBook = repository.save(b);
            log.info("Book '{}' successfully saved!", savedBook.getTitle());
            return savedBook;
        }
    }

    public Page<Book> getTitle(String title, Pageable page) {
        return repository.findByTitle(title, page);
    }
    public Page<Book> getGenre(String genre, Pageable page) {
        return repository.findByGenre(genre, page);
    }
    public void deleteBook(Long id) {
        Optional<Book> ticket = repository.findById(id);
        if (ticket.isPresent()) {
            repository.deleteById(id);
        }
    }
}
