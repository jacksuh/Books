package com.genre.books.dto;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
