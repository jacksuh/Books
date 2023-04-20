package com.genre.books.repository;
import com.genre.books.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findByName(String name, Pageable page);
}
