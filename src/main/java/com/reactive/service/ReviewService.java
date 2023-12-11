package com.reactive.service;

import com.reactive.model.Review;
import com.reactive.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    public Mono<Review> saveReview(Review review) {

        //this will subscribe the method by default , no need to subscribe the publisher explicitly
        Mono<Review> reviewMono = reviewRepository.save(review);
//        reviewMono.log().subscribe();
        return reviewMono;

    }
}
