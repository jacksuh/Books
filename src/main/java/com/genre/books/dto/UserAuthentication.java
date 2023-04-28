package com.genre.books.dto;
import jakarta.validation.constraints.NotBlank;
public record UserAuthentication(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
