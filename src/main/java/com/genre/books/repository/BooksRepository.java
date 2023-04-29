package com.genre.books.repository;
import com.genre.books.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);

    Page<Book> findByTitle(String title, Pageable page);

    Page<Book> findByGenre(String genero, Pageable page);
}
