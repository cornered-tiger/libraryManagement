package com.vestas.libraryManagement.exception;

import static java.lang.String.format;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final int id) {
        super(format("User with id=%d not found", id));
    }
}
