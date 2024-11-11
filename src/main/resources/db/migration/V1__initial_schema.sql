CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    user_name VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE books
 (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    isbn VARCHAR(13) NOT NULL CHECK (length(isbn) BETWEEN 1 AND 13),
    price decimal(10, 2) NOT NULL CHECK (price > 0)
);

CREATE TABLE book_borrow_history
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    book_id BIGSERIAL NOT NULL,
    borrow_date DATE,
    return_date DATE
);