package com.genre.books.dto;
import com.genre.books.model.Author;
import com.genre.books.model.Book;
import java.util.List;
public record BookDetailingDto(
        Long id,
        String title,
        String genre,
        List<Author> author) {

    public BookDetailingDto(Book book){
        this(book.getId(), book.getTitle(), book.getGenre(), book.getAuthor());
    }
}
