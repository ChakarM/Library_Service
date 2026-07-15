package com.mddvc.library.repo;

import com.mddvc.library.model.Author;
import com.mddvc.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    Optional<Author> findByFullName(String name, String surname);

    List<Book> findBooksByAuthor(Author author);

    List<Author> findAll();


}
