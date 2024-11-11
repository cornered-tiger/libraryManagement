package com.vestas.libraryManagement.facade;

import com.vestas.libraryManagement.dto.request.CreateBookRequest;
import com.vestas.libraryManagement.dto.response.BookBorrowHistoryDTO;
import com.vestas.libraryManagement.dto.response.BookDTO;
import com.vestas.libraryManagement.entity.User;
import com.vestas.libraryManagement.exception.BookNotFoundException;

import java.util.List;

public interface LibraryFacade {

    List<BookDTO> getAllBooks();

    List<BookDTO> getAvailableBooks();

    BookDTO getBookById(Long bookId) throws BookNotFoundException;

    BookDTO createBook(CreateBookRequest request);

    BookDTO updateBook(Long bookId, CreateBookRequest request) throws BookNotFoundException;

    void deleteBook(Long bookId);

    void borrowBook(Long bookId, Long userId);

    void returnBook(final Long bookId, Long userId);

    List<BookBorrowHistoryDTO> getBorrowHistory(Long bookId);

    List<User> createUser(User user);

}
