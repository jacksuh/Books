package com.genre.books.repository;
import com.genre.books.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BooksRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitle(String title, Pageable page);

    Page<Book> findByGenre(String genero, Pageable page);
}
