package com.genre.books.service;
import com.genre.books.dto.UserDto;
import com.genre.books.exception.ValidationException;
import com.genre.books.model.User;
import com.genre.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    public Page<User> getAll(Pageable page) {
        return repository.findAll(page);
    }

    public User saveUser(UserDto dto) {
        UserDetails login = repository.findByLogin(dto.login());
        if (login != null) {
            throw new ValidationException("login already exists!");
        }else {
            User u = new User();
            u.setLogin(dto.login());
            u.setPassword(encoder.encode(dto.password()));
            return repository.save(u);
        }
    }

    public User updateUser(UserDto dto, Long id) {
        Optional<User> optional = repository.findById(id);
        User db = optional.get();
        db.setLogin(dto.login());
        db.setPassword(encoder.encode(dto.password()));

        return repository.save(db);
    }

    public void deleteuser(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            repository.deleteById(id);
        }

    }
}
