package com.vestas.libraryManagement.events;

import com.vestas.libraryManagement.entities.Book;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;

@Data
public class BookBorrowedEvent extends ApplicationEvent {
    private Book book;

    public BookBorrowedEvent(final Object source, final Book book) {
        super(source);
        this.book = book;
    }
}
