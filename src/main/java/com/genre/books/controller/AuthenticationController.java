package com.genre.books.controller;
import com.genre.books.dto.TokenJwtDto;
import com.genre.books.dto.UserAuthentication;
import com.genre.books.dto.UserDetalingDto;
import com.genre.books.dto.UserDto;
import com.genre.books.infra.springsecurity.TokenService;
import com.genre.books.model.User;
import com.genre.books.service.AutenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
@RestController
@RequestMapping("login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticationService service;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserAuthentication dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDto(tokenJWT));
    }


    @PostMapping("/new")
    public ResponseEntity savelogin(@RequestBody @Valid UserDto dto, UriComponentsBuilder uriBuilder){
        User u = service.saveUser(dto);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(u.getLogin()).toUri();
        return ResponseEntity.created(uri).body(new UserDetalingDto(u));
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllClient(@PageableDefault(sort = "id", page = 0, size = 10) Pageable page){
        Page<User> listUser = service.getAll(page);
        return ResponseEntity.ok(listUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserDto dto){
        User p = service.updateUser(dto,id);

        return p != null?
                ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        service.deleteuser(id);
        return ResponseEntity.ok().build();
    }

}
