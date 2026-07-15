package com.mddvc.library.util;

import com.mddvc.library.dto.AuthorDto;
import com.mddvc.library.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public Author dtoToAuthor(AuthorDto authorDto) {
        return new Author(authorDto.name(), authorDto.surname());
    }

    public AuthorDto authorToDto(Author author) {
        return new AuthorDto(author.getName(), author.getSurname());
    }
}
