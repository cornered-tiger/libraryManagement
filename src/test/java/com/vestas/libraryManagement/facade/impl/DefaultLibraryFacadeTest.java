package com.vestas.libraryManagement.facade.impl;

import com.vestas.libraryManagement.dto.request.BookRequest;
import com.vestas.libraryManagement.entity.Book;
import com.vestas.libraryManagement.mapper.BookBorrowHistoryMapper;
import com.vestas.libraryManagement.mapper.BookMapper;
import com.vestas.libraryManagement.repository.BookBorrowHistoryRepository;
import com.vestas.libraryManagement.repository.BookRepository;
import com.vestas.libraryManagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultLibraryFacadeTest {

    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String ISBN = "isbn";

    @InjectMocks
    private DefaultLibraryFacade testObj;

    @Mock
    private BookRepository bookRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private BookBorrowHistoryRepository bookBorrowHistoryRepositoryMock;

    @Mock
    private ApplicationEventPublisher applicationEventPublisherMock;

    @Mock
    private BookMapper bookMapperMock;

    @Mock
    private Book bookEntityMock;

    @Mock
    private BookRequest bookRequestMock;

    @Mock
    private BookBorrowHistoryMapper bookBorrowHistoryMapperMock;

    @BeforeEach()
    public void setup() {
        when(bookRepositoryMock.save(bookEntityMock)).thenReturn(bookEntityMock);
        when(bookRepositoryMock.findById(anyInt())).thenReturn(Optional.of(bookEntityMock));
        when(bookRequestMock.getAuthor()).thenReturn(AUTHOR);
        when(bookRequestMock.getTitle()).thenReturn(TITLE);
        when(bookRequestMock.getIsbn()).thenReturn(ISBN);
        when(bookRequestMock.getPrice()).thenReturn(BigDecimal.TEN);

    }

    @Test
    public void createBook_should_save_book() {

        // Given
        when(bookMapperMock.mapRequestToEntity(bookRequestMock)).thenReturn(bookEntityMock);

        // When
        testObj.createBook(bookRequestMock);

        // Then
        verify(bookRepositoryMock).save(bookEntityMock);
        verify(bookMapperMock).mapEntityToDTO(bookEntityMock);
    }

    @Test
    public void updateBook_should_update_book() {

        // When
        testObj.updateBook(1, bookRequestMock);

        // Then
        verify(bookRepositoryMock).save(bookEntityMock);
        verify(bookMapperMock).mapEntityToDTO(bookEntityMock);
        verify(bookEntityMock).setAuthor(AUTHOR);
        verify(bookEntityMock).setTitle(TITLE);
        verify(bookEntityMock).setIsbn(ISBN);
        verify(bookEntityMock).setPrice((BigDecimal.TEN));
    }



}