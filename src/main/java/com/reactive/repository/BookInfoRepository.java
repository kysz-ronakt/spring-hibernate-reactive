package com.reactive.repository;

import com.reactive.model.BookInfo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends R2dbcRepository<BookInfo, Long> {
}
