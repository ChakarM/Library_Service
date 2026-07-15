package com.mddvc.library.controller;

import com.mddvc.library.dto.AuthorDto;
import com.mddvc.library.dto.BookDto;
import com.mddvc.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private final LibraryService libraryService;

    @Autowired
    public AuthorsController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return libraryService.findAllAuthors();
    }

    @GetMapping("/books")
    public List<BookDto> getBooksByAuthor(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return libraryService.findBooksByAuthor(name, surname);
    }


}
