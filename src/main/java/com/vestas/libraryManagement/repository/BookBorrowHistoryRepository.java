package com.vestas.libraryManagement.repository;

import com.vestas.libraryManagement.entity.Book;
import com.vestas.libraryManagement.entity.BookBorrowHistory;
import com.vestas.libraryManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookBorrowHistoryRepository extends JpaRepository<BookBorrowHistory, Integer> {

    List<BookBorrowHistory> findByBookId(int bookId);

    Optional<BookBorrowHistory> findTopByBookAndUserAndReturnDateIsNullOrderByBorrowDateDesc(Book book, User user);

}
