package com.mddvc.library.util;

import com.mddvc.library.dto.AuthorDto;
import com.mddvc.library.dto.BookDto;
import com.mddvc.library.model.Author;
import com.mddvc.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book dtoToBook(BookDto bookDto) {
        Author author = new Author(bookDto.author().name(), bookDto.author().surname());
        return new Book(bookDto.title(), bookDto.price(), author);
    }

    public BookDto bookToDto(Book book) {
        AuthorDto authorDto = new AuthorDto(book.getAuthor().getName(), book.getAuthor().getSurname());
        return new BookDto(book.getTitle(), authorDto, book.getPrice());
    }
}
