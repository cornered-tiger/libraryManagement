CREATE TABLE users
(
    id integer NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1),
    name text NOT NULL CHECK (length(name) BETWEEN 1 AND 254) UNIQUE,
    password text NOT NULL,
    role text NOT NULL
);


CREATE TABLE books
 (
    id integer NOT NULL PRIMARY KEY GENERATED ALWAYS
        AS IDENTITY (START WITH 1),
    title text NOT NULL,
    isbn text NOT NULL CHECK (length(isbn) BETWEEN 1 AND 13),
    author text NOT NULL,
    price decimal(10, 2) NOT NULL CHECK (price > 0),
    is_available boolean
);

CREATE TABLE book_borrow_history
(
    id integer NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1),
    user_id int NOT NULL,
    book_id int NOT NULL,
    is_late_return boolean,
    borrow_date DATE,
    return_date DATE
);