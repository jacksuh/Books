package com.genre.books.dto;
import com.genre.books.model.User;

public record UserDetalingDto(Long id, String login) {

    public UserDetalingDto(User user){
        this(user.getId(), user.getLogin());
    }
}
