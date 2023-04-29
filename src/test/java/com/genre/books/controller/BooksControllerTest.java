package com.genre.books.controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser
class BooksControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test erro 400")
    @WithMockUser
    void Error400() throws Exception {
        var response = mvc.perform(post("/livros"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Test  201 - Salvar dados")
    @WithMockUser
    void books() throws Exception {
        String json = "{\"title\":\"book1\",\"genre\":\"vixe\",\"author\":[{\"name\":\"Jackson\"}]}";

        var response = mvc.perform(
                        post("/livros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());


    }

    @Test
    @DisplayName("Pesquisar lista completa")
    void getBooks()throws Exception {
        var response = mvc
                .perform(get("/livros")
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Pesquisar por titulo do livro")
    void getBooksTitle() throws Exception{

        var response = mvc.perform(
                        get("/livros/titulo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("titulo", "book1")
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Pesquisar por genero")
    void getGenre() throws Exception{

        var response = mvc.perform(
                        get("/livros/genero")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("genero", "vixe")
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    @DisplayName("Por nome do autor completo ou primeiro nome")
    void getAuthor() throws Exception {

        var response = mvc.perform(
                        get("/livros/author")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("author", "Jackson")
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Pesquisar por inicial ou o primeiro nome")
    void getAuthorName()throws Exception {

        var response = mvc.perform(
                        get("/livros/name")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("name", "J")
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Test Delete")
    void deleteBook()throws Exception {
        var response = mvc.perform(
                        delete("/livros/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}