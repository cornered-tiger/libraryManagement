package com.vestas.libraryManagement.controller;

import com.vestas.libraryManagement.dto.request.BorrowBookRequest;
import com.vestas.libraryManagement.dto.request.CreateBookRequest;
import com.vestas.libraryManagement.dto.request.ReturnBookRequest;
import com.vestas.libraryManagement.dto.response.BookBorrowHistoryDTO;
import com.vestas.libraryManagement.dto.response.BookDTO;
import com.vestas.libraryManagement.exception.BookNotFoundException;
import com.vestas.libraryManagement.facade.LibraryFacade;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
@Validated
public class BookController {

    @Autowired
    private LibraryFacade libraryFacade;

    @Secured("ROLE_OWNER")
    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody @Valid final CreateBookRequest request) {
        final var createdBook = libraryFacade.createBook(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{bookId}")
                .buildAndExpand(createdBook.getId()).toUri();
        return ResponseEntity.created(location).body(createdBook);
    }

    @Secured("ROLE_OWNER")
    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> update(@PathVariable final Long bookId, @RequestBody @Valid final CreateBookRequest request) throws BookNotFoundException {
        return ResponseEntity.ok().body(libraryFacade.updateBook(bookId, request));
    }

    @Secured({"ROLE_OWNER", "ROLE_CLIENT"})
    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok().body(libraryFacade.getAllBooks());
    }

    @Secured({"ROLE_OWNER", "ROLE_CLIENT"})
    @GetMapping("/available")
    public ResponseEntity<List<BookDTO>> getAvailableBooks() {
        return ResponseEntity.ok().body(libraryFacade.getAvailableBooks());
    }

    @Secured({"ROLE_OWNER", "ROLE_CLIENT"})
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable final Long bookId) throws BookNotFoundException {
        return ResponseEntity.ok().body(libraryFacade.getBookById(bookId));
    }

    @Secured("ROLE_OWNER")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable final Long bookId) throws BookNotFoundException {
        libraryFacade.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @Secured("ROLE_CLIENT")
    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<Void> borrow(@PathVariable final Long bookId, @RequestBody final BorrowBookRequest request) {
        libraryFacade.borrowBook(bookId, request.getUserId());
        return ResponseEntity.ok().build();
    }

    @Secured("ROLE_CLIENT")
    @PostMapping("/{bookId}/return")
    public ResponseEntity<Void> returnBook(@PathVariable final Long bookId, @RequestBody final ReturnBookRequest request) {
        libraryFacade.returnBook(bookId, request.getUserId());
        return ResponseEntity.ok().build();
    }

    @Secured("ROLE_OWNER")
    @GetMapping("/{bookId}/borrow/history")
    public ResponseEntity<List<BookBorrowHistoryDTO>> getBorrowHistory(@PathVariable final Long bookId) {
        return ResponseEntity.ok().body(libraryFacade.getBorrowHistory(bookId));
    }
}
