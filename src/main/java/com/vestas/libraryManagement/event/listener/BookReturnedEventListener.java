package com.vestas.libraryManagement.event.listener;

import com.vestas.libraryManagement.entity.BookBorrowHistory;
import com.vestas.libraryManagement.event.BookReturnedEvent;
import com.vestas.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Component
public class BookReturnedEventListener {

    @Value("${book-return.late-return-period}")
    private Period LATE_RETURN_PERIOD;

    private final BookRepository bookRepository;

    public BookReturnedEventListener(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener
    public void listen(final BookReturnedEvent event) {
        Optional.of(event.getBookBorrowHistory())
                .ifPresent(bookBorrowHistory -> {
                    final var book = bookBorrowHistory.getBook();
                    book.setAvailable(true);
                    bookRepository.save(book);
                    bookBorrowHistory.setLateReturn(isLateReturn(bookBorrowHistory));
                });
    }

    public boolean isLateReturn(final BookBorrowHistory bookBorrowHistory) {
        return LocalDate.now().isAfter(bookBorrowHistory.getBorrowDate().plus(LATE_RETURN_PERIOD));
    }
}
