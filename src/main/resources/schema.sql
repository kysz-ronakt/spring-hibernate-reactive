CREATE TABLE IF NOT EXISTS tutorial (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    published BOOLEAN
);

-- src/main/resources/schema.sql
CREATE TABLE IF NOT EXISTS authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT
);

CREATE TABLE IF NOT EXISTS review (
    review_id SERIAL PRIMARY KEY,
    ratings DOUBLE PRECISION,
    comments VARCHAR(255)
--    FOREIGN KEY (new_book_id) REFERENCES new_book(new_book_id)
);

CREATE TABLE IF NOT EXISTS book_info (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    isbn VARCHAR(255),
    review_id BIGINT,
    FOREIGN KEY (review_id) REFERENCES review(review_id)
);


--
--
--CREATE TABLE IF NOT EXISTS new_book (
--    new_book_id SERIAL PRIMARY KEY,
--    book_info_id INT,
--    FOREIGN KEY (book_info_id) REFERENCES book_info(book_id)
--);


--    MANY TO MANY RELATIONSHIP

--CREATE TABLE IF NOT EXISTS new_book (
--    new_book_id SERIAL PRIMARY KEY,
--    book_info_id INT,
--    FOREIGN KEY (book_info_id) REFERENCES book_info(book_id)
--);
--
--
--
--CREATE TABLE IF NOT EXISTS new_book_review (
--    new_book_id INT,
--    review_id BIGINT,
--    FOREIGN KEY (new_book_id) REFERENCES new_book(new_book_id),
--    FOREIGN KEY (review_id) REFERENCES review(review_id),
--    PRIMARY KEY (new_book_id, review_id)
--);
