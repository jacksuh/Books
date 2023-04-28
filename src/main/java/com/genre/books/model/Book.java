package com.genre.books.model;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private String genre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name= "Book_Author",
            joinColumns = {  @JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "author_id")}
    )
    private List<Author> author;


}
