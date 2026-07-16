package com.mddvc.library.repo;

import com.mddvc.library.model.Author;
import com.mddvc.library.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImp implements BookRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void removeBook(Book book) {
        sessionFactory.getCurrentSession().remove(book);
    }

    @Override
    public Book save(Book book) {
        sessionFactory.getCurrentSession().persist(book);
        return book;
    }


    @Override
    public Optional<Book> findByTitle(String title) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Book where title = :title", Book.class)
                .setParameter("title", title)
                .uniqueResultOptional();
    }


    @Override
    public List<Book> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Book", Book.class).getResultList();
    }
}
