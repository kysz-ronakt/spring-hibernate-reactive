package com.reactive.service;

import com.reactive.dto.AuthorBookDTO;
import com.reactive.model.Author;
import com.reactive.model.Book;
import com.reactive.repository.AuthorRepository;
import com.reactive.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Mono<Author> saveAuthorWithBook(AuthorBookDTO authorBookDTO) {

        Author author = new Author();
        author.setName(authorBookDTO.getAuthorName());

        Book book = new Book();
        book.setTitle(authorBookDTO.getBookTitle());

        Mono<Author> authorMono = authorRepository.save(author)
                .flatMap(savedAuthor -> {
                    book.setAuthorId(savedAuthor.getId());
                    Mono<Book> save = bookRepository.save(book);
                    return save;
                })
                .map(savedBook -> author);

        return authorMono;
    }

}
