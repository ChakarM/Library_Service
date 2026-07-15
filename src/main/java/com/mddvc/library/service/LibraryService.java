package com.mddvc.library.service;

import com.mddvc.library.dto.AuthorDto;
import com.mddvc.library.dto.BookDto;
import com.mddvc.library.exception.ServiceException;
import com.mddvc.library.model.Author;
import com.mddvc.library.model.Book;
import com.mddvc.library.repo.AuthorRepository;
import com.mddvc.library.repo.AuthorRepositoryImpl;
import com.mddvc.library.repo.BookRepository;
import com.mddvc.library.repo.BookRepositoryImp;
import com.mddvc.library.util.AuthorMapper;
import com.mddvc.library.util.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepositoryImp;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @Autowired
    public LibraryService(AuthorRepositoryImpl authorRepository, BookRepositoryImp bookRepositoryImp,
                          AuthorMapper authorMapper, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.bookRepositoryImp = bookRepositoryImp;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
    }


    public BookDto saveBook(BookDto bookDto) {
        String name = bookDto.author().name();
        String surname = bookDto.author().surname();

        Author author = authorRepository.findByFullName(name, surname).orElseGet(() -> {
            Author newAuthor = new Author(name, surname);
            return authorRepository.save(newAuthor);
        });

        Book book = author.writeBook(bookDto.title(), bookDto.price());
        Book savedBook = bookRepositoryImp.save(book);
        return bookMapper.bookToDto(savedBook);
    }

    @Transactional(readOnly = true)
    public List<BookDto> findBooksByAuthor(String name, String surname) {
        Author author = authorRepository.findByFullName(name, surname)
                .orElseThrow(() -> new ServiceException("Author not found"));
        return authorRepository
                .findBooksByAuthor(author)
                .stream()
                .map(bookMapper::bookToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> findAllAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(authorMapper::authorToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookDto findBookByTitle(String title) {
        Book book = bookRepositoryImp.findByTitle(title)
                .orElseThrow(() -> new ServiceException("Book not found"));
        return bookMapper.bookToDto(book);
    }

    @Transactional(readOnly = true)
    public List<BookDto> findAllBooks() {
        return bookRepositoryImp
                .findAll()
                .stream()
                .map(bookMapper::bookToDto)
                .toList();
    }
}
