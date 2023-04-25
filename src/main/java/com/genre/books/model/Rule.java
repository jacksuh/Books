package com.genre.books.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
@Entity(name = "Rule")
@Getter
@Setter
public class Rule implements GrantedAuthority {


    @Id
    private String nameRole;

    @ManyToMany
    private List<User> users;

    @Override
    public String getAuthority() {
        return this.nameRole;
    }

}
