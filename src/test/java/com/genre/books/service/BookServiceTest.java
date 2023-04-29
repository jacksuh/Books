package com.genre.books.service;

import com.genre.books.dto.BookDto;
import com.genre.books.exception.ValidationException;
import com.genre.books.model.Author;
import com.genre.books.model.Book;
import com.genre.books.repository.BooksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BooksRepository repository;
    @Test
    @DisplayName("Should save a book")
    public void shouldCreateANewBook() {

        //given
        Author a = new Author();
        a.setName("Jackson");
        List<Author> author1 = new ArrayList<>();
        author1.add(a);
        BookDto bookDto = new BookDto("Jackson", "j", author1);

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setGenre("j");
        expectedBook.setTitle("Jackson");
        Author authorExpected = new Author();
        authorExpected.setName("Jackson");
        expectedBook.setAuthor(Collections.singletonList(authorExpected));
        when(repository.save(any(Book.class))).thenReturn(expectedBook);

        //when
        Book savedBook = bookService.saveBook(bookDto);

        //then
        assertThat(savedBook).isEqualTo(expectedBook);
    }
    @Test
    @DisplayName("Should throws an ValidationException")
    public void shouldThrowsAnException() {

        //given
        Author a = new Author();
        a.setName("Jackson");
        List<Author> author1 = new ArrayList<>();
        author1.add(a);
        BookDto bookDto = new BookDto("Jackson", "j", author1);

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setGenre("j");
        expectedBook.setTitle("Jackson");
        Author authorExpected = new Author();
        authorExpected.setName("Jackson");
        expectedBook.setAuthor(Collections.singletonList(authorExpected));
        when(repository.findByTitle(anyString())).thenReturn(expectedBook);

        //when
        ValidationException thrown = Assertions.assertThrows(ValidationException.class, () -> {
            bookService.saveBook(bookDto);
        });

        //then
        assertThat(thrown).isInstanceOf(ValidationException.class);
        assertThat(thrown.getMessage()).isEqualTo("Book already exists!");
    }

    @Test
    public void getAll() {

        Pageable page = PageRequest.of(0, 10);

        List<Book> books1 = new ArrayList<Book>();
        Book b = new Book();
        b.setGenre("Jackson");
        b.setTitle("jackson");

        books1.add(b);

        Page<Book> pages = new PageImpl<>(books1, page, 0);
        when(repository.findAll(page)).thenReturn(pages);

        Page<Book> book = bookService.getAll(page);

        Book books3 = books1.get(0);
        Book books2 = book.stream().findAny().get();

        assertThat(book).isNotEmpty();
        assertThat(books2.getTitle()).isEqualTo(books3.getTitle());

    }

    @Test
    public void getTitle() {

        Pageable page = PageRequest.of(0, 10);

        List<Book> books1 = new ArrayList<Book>();
        Book b = new Book();
        b.setGenre("Jackson");
        b.setTitle("book");

        books1.add(b);

        Page<Book> pages = new PageImpl<>(books1, page, 0);

        when(repository.findByTitle("book", page)).thenReturn(pages);

        Page<Book> book = bookService.getTitle("book", page);

        Book books3 = books1.get(0);
        Book books2 = book.stream().findAny().get();


        assertThat(book).isNotEmpty();
        assertThat(books2.getTitle()).isEqualTo(books3.getTitle());
        assertThat(books2.getGenre()).isEqualTo("Jackson");


    }


    @Test
    public void getGenero() {

        Pageable page = PageRequest.of(0, 10);

        List<Book> books1 = new ArrayList<Book>();
        Book b = new Book();
        b.setGenre("Jackson");
        b.setTitle("book");

        books1.add(b);

        Page<Book> pages = new PageImpl<>(books1, page, 0);

        when(repository.findByTitle("book", page)).thenReturn(pages);

        Page<Book> book = bookService.getTitle("book", page);

        Book books3 = books1.get(0);
        Book books2 = book.stream().findAny().get();


        assertThat(book).isNotEmpty();
        assertThat(books2.getTitle()).isEqualTo(books3.getTitle());
        assertThat(books2.getGenre()).isEqualTo("Jackson");
    }


}