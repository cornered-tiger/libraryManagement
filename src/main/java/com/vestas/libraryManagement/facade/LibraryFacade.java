package com.vestas.libraryManagement.facade;

import com.vestas.libraryManagement.dto.request.BookRequest;
import com.vestas.libraryManagement.dto.response.BookBorrowHistoryDTO;
import com.vestas.libraryManagement.dto.response.BookDTO;
import com.vestas.libraryManagement.entity.User;
import com.vestas.libraryManagement.exception.BookNotFoundException;

import java.util.List;

public interface LibraryFacade {

    List<BookDTO> getAllBooks();

    List<BookDTO> getAvailableBooks();

    BookDTO getBookById(int bookId) throws BookNotFoundException;

    BookDTO createBook(BookRequest request);

    BookDTO updateBook(int bookId, BookRequest request) throws BookNotFoundException;

    void deleteBook(int bookId);

    void borrowBook(int bookId, int userId);

    void returnBook(final int bookId, int userId);

    List<BookBorrowHistoryDTO> getBorrowHistory(int bookId);

    List<User> createUser(User user);

}
