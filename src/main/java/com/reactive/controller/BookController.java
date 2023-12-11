package com.reactive.controller;

import com.reactive.dto.BookReviewDTO;
import com.reactive.model.Book;
import com.reactive.model.BookInfo;
import com.reactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookService bookService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookInfo> createAuthor(@RequestBody BookReviewDTO bookReviewDTO) {
        return bookService.createBookInfoAlongWithReview(bookReviewDTO);
    }

    @GetMapping("/findByAuthorId/{id}")
    public Flux<Book> findBooksByAuthorId(@PathVariable Long id) {
        return bookService.findBooksByAuthorId(id);
    }

    @GetMapping("/title/{title}")
    public Mono<Book> findBookByTitle(@PathVariable String title) {
        return bookService.findBookByTitle(title);
    }

    @GetMapping("/findAll")
    public Flux<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping("/bookInfo")
    public Flux<BookInfo> getAllBookInfo() {
        return bookService.getAllBookInfo();
    }

    @GetMapping("/bookInfo/{id}")
    public Mono<BookInfo> getBookInfoWithReview(@PathVariable long id) {
        return bookService.getBookInfoWithReview(id);
    }
}
