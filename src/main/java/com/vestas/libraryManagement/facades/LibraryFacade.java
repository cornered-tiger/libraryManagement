package com.vestas.libraryManagement.facades;

import com.vestas.libraryManagement.dtos.request.CreateBookRequest;
import com.vestas.libraryManagement.dtos.response.BookBorrowHistoryDTO;
import com.vestas.libraryManagement.dtos.response.BookDTO;
import com.vestas.libraryManagement.entities.Book;
import com.vestas.libraryManagement.entities.BookBorrowHistory;
import com.vestas.libraryManagement.entities.User;
import com.vestas.libraryManagement.exceptions.BookNotFoundException;

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
