package com.genre.books.dto;
import com.genre.books.model.Author;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record BookDto(
        @NotBlank
        String title,
        @NotBlank
        String genre,

        @NotBlank
        List<Author> author) {
}
