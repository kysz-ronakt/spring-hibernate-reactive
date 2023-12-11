package com.reactive.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.function.BiFunction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("authors")
@Entity
public class Author {

    @Id
    private Long id;
    private String name;

    public static void main(String[] args) {
        //open case
        BiFunction<String, String, String> biFunction1 = String::concat;

        System.out.println(biFunction1.apply("java8 ", "biFunction"));

        System.out.println();
    }
}
