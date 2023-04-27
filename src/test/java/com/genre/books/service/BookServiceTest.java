package com.genre.books.service;
import com.genre.books.dto.BookDto;
import com.genre.books.model.Author;
import com.genre.books.model.Book;
import com.genre.books.repository.BooksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

@SpringBootTest
@WithMockUser
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BookServiceTest {

    @Autowired
    private BookService bookService;


    @Test
    public void createBook(){

        Author a  = new Author();
        a.setName("Jackson");
        List<Author> author1 = new ArrayList<Author>();
        author1.add(a);
        BookDto l = new BookDto("Jackson", "j", author1);

        Book bo = bookService.saveBook(l);

        List<Book> books1 = new ArrayList<Book>();
        Book b = new Book();
        b.setAuthor(l.author());
        b.setGenre(l.genre());
        b.setTitle(l.title());

        books1.add(b);

       // when(repository.saveAll(books1)).thenReturn(books1);

        assertThat(bo.getTitle()).isEqualTo(l.title());
    }

    @Test
    public void getAll() {

        Pageable page = PageRequest.of(0, 10);

        Page<Book> c = bookService.getAll(page);

        assertThat(c).isNull();
    }

    @Test
    public void getTitle(){

        Pageable page = PageRequest.of(0, 10);

        Page<Book> bd =  bookService.getTitle("book1", page);

        assertThat(bd).isNotEmpty();

    }


    @Test
    public void getGenero(){
        Pageable page = PageRequest.of(0, 10);

        Page<Book> bd =  bookService.getGenero("vixe", page);

        assertThat(bd).isNotEmpty();

    }


}