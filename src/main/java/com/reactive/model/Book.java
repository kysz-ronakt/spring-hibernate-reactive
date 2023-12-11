package com.reactive.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("books")
@Entity
public class Book {

    @Id
    private Long id;

    private String title;
    @Column("author_id")
    private Long authorId;
}
