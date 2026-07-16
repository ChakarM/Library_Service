package com.mddvc.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "authors",
        indexes = {@Index
                (name = "idx_author_full_name",
                        columnList = "name, surname")})
public class Author {

    @Getter
    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
    @SequenceGenerator(name = "author_seq", sequenceName = "author_id_seq", allocationSize = 10)
    private Long id;

    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Book> books = new ArrayList<>();


    public Author(String name, String surname) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("First name cannot be empty");
        if (surname == null || surname.isBlank()) throw new IllegalArgumentException("Surname cannot be empty");
        this.name = name;
        this.surname = surname;
    }

    public Book writeBook(String title, BigDecimal price){
        Book book = new Book(title, price, this);
        books.add(book);
        return book;
    }

    public void deleteBook(Book book){
      this.books.remove(book);
    }

    public List<Book> getBooks(){
        return Collections.unmodifiableList(this.books);
    }

}
