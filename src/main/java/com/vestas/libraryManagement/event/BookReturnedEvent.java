package com.vestas.libraryManagement.event;

import com.vestas.libraryManagement.entity.Book;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class BookReturnedEvent extends ApplicationEvent {
    private final Book book;

    public BookReturnedEvent(final Object source, final Book book) {
        super(source);
        this.book = book;
    }
}
