package com.vestas.libraryManagement.facade.impl;

import com.vestas.libraryManagement.dto.request.CreateBookRequest;
import com.vestas.libraryManagement.dto.response.BookBorrowHistoryDTO;
import com.vestas.libraryManagement.dto.response.BookDTO;
import com.vestas.libraryManagement.entity.BookBorrowHistory;
import com.vestas.libraryManagement.entity.User;
import com.vestas.libraryManagement.event.BookBorrowedEvent;
import com.vestas.libraryManagement.event.BookReturnedEvent;
import com.vestas.libraryManagement.exception.BookNotFoundException;
import com.vestas.libraryManagement.exception.UserNotFoundException;
import com.vestas.libraryManagement.facade.LibraryFacade;
import com.vestas.libraryManagement.mapper.BookBorrowHistoryMapper;
import com.vestas.libraryManagement.mapper.BookMapper;
import com.vestas.libraryManagement.repository.BookBorrowHistoryRepository;
import com.vestas.libraryManagement.repository.BookRepository;
import com.vestas.libraryManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DefaultLibraryFacade implements LibraryFacade {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final BookBorrowHistoryRepository bookBorrowHistoryRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final BookMapper bookMapper;

    private final BookBorrowHistoryMapper bookBorrowHistoryMapper;

    public DefaultLibraryFacade(final BookRepository bookRepository, final UserRepository userRepository, final BookBorrowHistoryRepository bookBorrowHistoryRepository, final ApplicationEventPublisher eventPublisher, final BookMapper bookMapper, final BookBorrowHistoryMapper bookBorrowHistoryMapper) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookBorrowHistoryRepository = bookBorrowHistoryRepository;
        this.eventPublisher = eventPublisher;
        this.bookMapper = bookMapper;
        this.bookBorrowHistoryMapper = bookBorrowHistoryMapper;
    }

    @Override
    public BookDTO createBook(final CreateBookRequest createBookRequest) {

        final var book = bookRepository.save(bookMapper.mapRequestToEntity(createBookRequest));
        return bookMapper.mapEntityToDTO(book);
    }

    @Override
    public BookDTO updateBook(final Long bookId, final CreateBookRequest request) throws BookNotFoundException {
        final var book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setAuthor(request.getAuthor());
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
        eventPublisher.publishEvent(new BookBorrowedEvent(eventPublisher, book));
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
        eventPublisher.publishEvent(new BookReturnedEvent(eventPublisher, book));

    }

    @Override
    public List<BookBorrowHistoryDTO> getBorrowHistory(final Long bookId) {
        return bookBorrowHistoryRepository.findByBookId(bookId).stream().map(bookBorrowHistoryMapper::mapEntityToDTO).toList();
    }

    @Override
    public List<User> createUser(User user) {
        userRepository.save(user);
        return userRepository.findAll();
    }
}
