package com.mddvc.library.controller;

import com.mddvc.library.dto.BookDto;
import com.mddvc.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final LibraryService libraryService;

    @Autowired
    public BooksController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return libraryService.findAllBooks();
    }

    @GetMapping("/{title}")
    public BookDto getBookByTitle(@PathVariable String title){
        return libraryService.findBookByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto saveBook(@RequestBody BookDto bookDto) {
        return libraryService.saveBook(bookDto);
    }

    @PutMapping("/{title}")
    public BookDto updateBookPrice(@PathVariable("title") String title,
                                   @RequestParam("newPrice")BigDecimal newPrice) {
        return libraryService.changeBookPrice(title, newPrice);
    }

    @DeleteMapping("/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("title") String title) {
        libraryService.deleteBook(title);
    }
}
