package com.mddvc.library.repo;

import com.mddvc.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findByTitle(String title);

    List<Book> findAll();
}
