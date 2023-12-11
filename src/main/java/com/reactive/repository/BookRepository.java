package com.reactive.repository;

import com.reactive.model.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Long> {

    Flux<Book> findByAuthorId(Long authorId);

    Mono<Book> findByTitleContaining(String title);
}
