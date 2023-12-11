package com.reactive.controller;


import com.reactive.dto.AuthorBookDTO;
import com.reactive.model.Author;
import com.reactive.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authors")
public class AuthorController {


    @Autowired
    private AuthorService authorService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Author> createAuthor(@RequestBody AuthorBookDTO authorBookDTO) {
        return authorService.saveAuthorWithBook(authorBookDTO);
    }


}
