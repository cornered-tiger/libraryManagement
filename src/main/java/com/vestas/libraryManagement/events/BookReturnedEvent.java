package com.vestas.libraryManagement.events;

import com.vestas.libraryManagement.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;

@Data
public class BookReturnedEvent extends ApplicationEvent {
    private final Book book;

    public BookReturnedEvent(final Object source, final Book book) {
        super(source);
        this.book = book;
    }
}
