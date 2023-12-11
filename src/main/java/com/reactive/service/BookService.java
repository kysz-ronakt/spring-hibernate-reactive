package com.reactive.service;

import com.reactive.dto.BookReviewDTO;
import com.reactive.model.Book;
import com.reactive.model.BookInfo;
import com.reactive.model.Review;
import com.reactive.repository.BookInfoRepository;
import com.reactive.repository.BookRepository;
import com.reactive.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }

    public Flux<Book> findBooksByAuthorId(@PathVariable Long id) {
        return bookRepository.findByAuthorId(id);
    }

    public Mono<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleContaining(title)
                .log()
                .switchIfEmpty(Mono.error(new RuntimeException("Book not found with title: " + title)));
    }


    public Flux<Book> getAllBooks() {
        return bookRepository.findAll()
                .log()
                .switchIfEmpty(Mono.error(new Exception("Something went wrong, unable to fetch records.")));
    }


    // FIXME: 28/11/2023 bookInfo methods

    /*public Mono<BookInfo> createBookInfoAlongWithReview(BookReviewDTO bookReviewDTO) {

        //save the review first

        Review review = new Review();
        review.setComments(bookReviewDTO.getComments());
        review.setRatings(bookReviewDTO.getRatings());


        //save the BookInfo first
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(bookReviewDTO.getTitle());
        bookInfo.setISBN(bookReviewDTO.getIsbn());
        bookInfo.setAuthor(bookReviewDTO.getAuthor());


        return reviewRepository.save(review)
                .flatMap(savedReview -> {
                    bookInfo.setReview(review);
                    return bookInfoRepository.save(bookInfo);
                });
    }*/

    public Mono<BookInfo> createBookInfoAlongWithReview(BookReviewDTO bookReviewDTO) {

        /*// Save the review first
        Review review = new Review();
        review.setComments(bookReviewDTO.getComments());
        review.setRatings(bookReviewDTO.getRatings());


        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(bookReviewDTO.getTitle());
        bookInfo.setISBN(bookReviewDTO.getIsbn());
        bookInfo.setAuthor(bookReviewDTO.getAuthor());


        return reviewRepository.save(review)
                .flatMap(savedReview -> {

                    System.out.println("savedReview = " + savedReview);
                    // Save the BookInfo with a reference to the saved Review
                    bookInfo.setReview(savedReview);
                    return bookInfoRepository.save(bookInfo);
                });*/

       /* // Create BookInfo entity
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(bookReviewDTO.getTitle());
        bookInfo.setAuthor(bookReviewDTO.getAuthor());
        bookInfo.setISBN(bookReviewDTO.getIsbn());

        // Create Review entity
        Review review = new Review();
        review.setRatings(bookReviewDTO.getRatings());
        review.setComments(bookReviewDTO.getComments());

        // Set the Review entity in the BookInfo entity
        bookInfo.setReview(review);

        // Save the BookInfo entity in the database using R2DBC
        return bookInfoRepository.save(bookInfo);
*/

        Review review = new Review();
        review.setRatings(bookReviewDTO.getRatings());
        review.setComments(bookReviewDTO.getComments());

        return reviewRepository.save(review)
                .flatMap(savedReview -> {
                    BookInfo bookInfo = new BookInfo();
                    bookInfo.setTitle(bookReviewDTO.getTitle());
                    bookInfo.setAuthor(bookReviewDTO.getAuthor());
                    bookInfo.setISBN(bookReviewDTO.getIsbn());
                    bookInfo.setReview(savedReview);

                    return bookInfoRepository.save(bookInfo);
                });
    }


    public Flux<BookInfo> getAllBookInfo() {
        return bookInfoRepository.findAll();

    }

    public Mono<BookInfo> getBookInfoWithReview(long id) {
        return bookInfoRepository.findById(id)
                .flatMap(bookInfo -> {
                    if (bookInfo != null && Objects.nonNull(bookInfo.getReview())) {
                        return reviewRepository.findById(bookInfo.getReview().getId())
                                .map(review -> {
                                    bookInfo.setReview(review);
                                    return bookInfo;
                                });
                    } else {
                        return Mono.just(bookInfo);
                    }
                });
    }

}