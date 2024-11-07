package com.vestas.libraryManagement.events.listeners;

import com.vestas.libraryManagement.entities.Book;
import com.vestas.libraryManagement.events.BookBorrowedEvent;
import com.vestas.libraryManagement.events.BookReturnedEvent;
import com.vestas.libraryManagement.repositories.BookRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookBorrowedEventListener {

    private final BookRepository bookRepository;

    public BookBorrowedEventListener(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * For demo purpose only
     */
    @EventListener
    public void listen(final BookBorrowedEvent event) {
        Optional.of(event.getBook())
                .ifPresent(book -> {
                    book.setAvailable(false);
                    bookRepository.save(book);
                });
    }
}
