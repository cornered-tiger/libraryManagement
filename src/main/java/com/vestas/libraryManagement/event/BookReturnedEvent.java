package com.vestas.libraryManagement.event;

import com.vestas.libraryManagement.entity.BookBorrowHistory;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class BookReturnedEvent extends ApplicationEvent {
    private final BookBorrowHistory bookBorrowHistory;

    public BookReturnedEvent(final Object source, final BookBorrowHistory bookBorrowHistory) {
        super(source);
        this.bookBorrowHistory = bookBorrowHistory;
    }
}
