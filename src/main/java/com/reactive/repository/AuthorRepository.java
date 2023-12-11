package com.reactive.repository;

import com.reactive.model.Author;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends R2dbcRepository<Author,Long> {
}
