package com.vestas.libraryManagement.services.impl;

import com.vestas.libraryManagement.dtos.response.BookDTO;
import com.vestas.libraryManagement.entities.Book;
import com.vestas.libraryManagement.entities.BookBorrowHistory;
import com.vestas.libraryManagement.entities.User;
import com.vestas.libraryManagement.events.BookBorrowedEvent;
import com.vestas.libraryManagement.events.BookReturnedEvent;
import com.vestas.libraryManagement.exceptions.BookNotFoundException;
import com.vestas.libraryManagement.exceptions.UserNotFoundException;
import com.vestas.libraryManagement.mappers.BookMapper;
import com.vestas.libraryManagement.repositories.BookBorrowHistoryRepository;
import com.vestas.libraryManagement.repositories.BookRepository;
import com.vestas.libraryManagement.repositories.UserRepository;
import com.vestas.libraryManagement.services.BookService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class DefaultBookService implements BookService {
    /*

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final BookBorrowHistoryRepository bookBorrowHistoryRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final BookMapper bookMapper;

    public DefaultBookService(final BookRepository bookRepository, final UserRepository userRepository, final BookBorrowHistoryRepository bookBorrowHistoryRepository, final ApplicationEventPublisher eventPublisher, final BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookBorrowHistoryRepository = bookBorrowHistoryRepository;
        this.eventPublisher = eventPublisher;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO createBook(final BookDTO bookDTO) {
        final var saved = bookRepository.save(bookMapper.mapDTOToEntity(bookDTO));
        return bookMapper.mapEntityToDTO(saved);
    }

    @Override
    public BookDTO updateBook(final Long bookId, final Book bookDTO) throws BookNotFoundException {
        final var book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setAuthor(bookDTO.getAuthor());

        bookRepository.save(book);

        return bookMapper.mapEntityToDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::mapEntityToDTO).toList();
    }

    @Override
    public List<BookDTO> getAvailableBooks() {
        return getAllBooks().stream().filter(BookDTO::isAvailable).toList();
    }

    @Override
    public BookDTO getBookById(final Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).map(bookMapper::mapEntityToDTO).orElseThrow(() -> new BookNotFoundException(bookId));
    }

    @Override
    public void deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional
    @Override
    public void borrowBook(final Long bookId, final Long userId) {
        final var book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        final var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is currently not available for borrowing");
        }
        final var bookBorrowHistory = BookBorrowHistory.builder()
                .book(book)
                .user(user)
                .borrowDate(LocalDate.now())
                .returnDate(null)
                .build();
        bookBorrowHistoryRepository.save(bookBorrowHistory);
        book.setAvailable(false);
        bookRepository.save(book);
        eventPublisher.publishEvent(BookBorrowedEvent.builder()
                .source(this)
                .bookId(bookId)
                .userId(userId)
                .borrowDate(LocalDate.now())
                .build());
    }

    @Transactional
    @Override
    public void returnBook(final Long bookId, final Long userId) {
        final var book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        final var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        final var bookBorrowHistory = bookBorrowHistoryRepository.findTopByBookAndUserAndReturnDateIsNullOrderByBorrowDateDesc(book, user).orElseThrow(() -> new BookNotFoundException(bookId));
        bookBorrowHistory.setReturnDate(LocalDate.now());
        bookBorrowHistoryRepository.save(bookBorrowHistory);
        book.setAvailable(true);
        bookRepository.save(book);
        eventPublisher.publishEvent(BookReturnedEvent.builder().source(this).bookId(userId).returnDate(LocalDate.now()).build());

    }

    @Override
    public List<BookBorrowHistory> getBorrowHistory(final Long bookId) {
        return bookBorrowHistoryRepository.findByBookId(bookId);
    }

     */

}
