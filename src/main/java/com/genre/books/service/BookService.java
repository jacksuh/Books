package com.genre.books.service;
import com.genre.books.dto.BookDto;
import com.genre.books.exception.ValidationException;
import com.genre.books.model.Book;
import com.genre.books.repository.AuthorRepository;
import com.genre.books.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private BooksRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    public Page<Book> getAll(Pageable page) {
        return repository.findAll(page);
    }



    public Book saveBook(BookDto dto) {
        Optional<Book> book = Optional.ofNullable(repository.findByTitle(dto.title()));

        if(book.isPresent()){
            throw new ValidationException("Book already exists!");
        }else {
            List<Book> books1 = new ArrayList<Book>();
            Book b = new Book();
            b.setAuthor(dto.author());
            b.setGenre(dto.genre());
            b.setTitle(dto.title());

            books1.add(b);

            List<Book> savedBook = repository.saveAll(books1);
            log.info("Book '{}' successfully saved!", savedBook);

            return b;
        }
    }


    public Page<Book> getTitle(String title, Pageable page) {
        return repository.findByTitle(title, page);
    }

    public Page<Book> getGenero(String genre, Pageable page) {
        return repository.findByGenre(genre, page);
    }


    public void deleteBook(Long id) {
        Optional<Book> ticket = repository.findById(id);
        if (ticket.isPresent()) {
            repository.deleteById(id);
        }

    }

}
