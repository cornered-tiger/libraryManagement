package com.vestas.libraryManagement.exception;

import static java.lang.String.format;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super(format("Book with id=%d not found", id));
    }
}
