package com.vestas.libraryManagement.repositories;

import com.vestas.libraryManagement.entities.Book;
import com.vestas.libraryManagement.entities.BookBorrowHistory;
import com.vestas.libraryManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookBorrowHistoryRepository extends JpaRepository<BookBorrowHistory, Long> {

    List<BookBorrowHistory> findByBookId(Long bookId);

    Optional<BookBorrowHistory> findTopByBookAndUserAndReturnDateIsNullOrderByBorrowDateDesc(Book book, User user);

}
