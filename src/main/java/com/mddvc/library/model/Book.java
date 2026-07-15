package com.mddvc.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "books", indexes = {@Index(name = "idx_book_title", columnList = "title", unique = true)})
public class Book {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_id_seq", allocationSize = 10)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Book(String title, BigDecimal price, Author author) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title can't be empty");
        if (price.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Price should be more than zero");
        this.title = title;
        this.price = price;
        this.author = author;
    }



}
