package com.vestas.libraryManagement.event;

import com.vestas.libraryManagement.entity.Book;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class BookBorrowedEvent extends ApplicationEvent {
    private Book book;

    public BookBorrowedEvent(final Object source, final Book book) {
        super(source);
        this.book = book;
    }
}
