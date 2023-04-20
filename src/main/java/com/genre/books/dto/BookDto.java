package com.genre.books.dto;
import com.genre.books.model.Author;
import java.util.List;

public record BookDto(String title, String genre, List<Author> author) {
}
