package com.vestas.libraryManagement.events.listeners;

import com.vestas.libraryManagement.events.BookReturnedEvent;
import com.vestas.libraryManagement.repositories.BookRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookReturnedEventListener {

    private final BookRepository bookRepository;

    public BookReturnedEventListener(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener
    public void listen(final BookReturnedEvent event) {
        Optional.of(event.getBook())
                .ifPresent(book -> {
                    book.setAvailable(true);
                    bookRepository.save(book);
                });
    }
}
