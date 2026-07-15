package com.mddvc.library.repo;

import com.mddvc.library.model.Author;
import com.mddvc.library.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AuthorRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Author> findByFullName(String name, String surname) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Author where name = :name and surname = :surname", Author.class)
                .setParameter("name", name)
                .setParameter("surname", surname)
                .uniqueResultOptional();
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        return author.getBooks();
    }

    @Override
    public List<Author> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Author", Author.class).getResultList();
    }

    @Override
    public Author save(Author author) {
        sessionFactory.getCurrentSession().persist(author);
        return author;
    }
}
