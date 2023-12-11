package com.reactive.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReviewDTO {

    //bookInfo
    private String title;
    private String author;
    private String isbn;

    //review
    private double ratings;
    private String comments;
}
