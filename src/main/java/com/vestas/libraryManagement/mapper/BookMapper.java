package com.vestas.libraryManagement.mapper;

import com.vestas.libraryManagement.dto.request.BookRequest;
import com.vestas.libraryManagement.dto.response.BookDTO;
import com.vestas.libraryManagement.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class is designed to prevent direct exposure of database entity objects to external systems.
 * It converts entity objects to DTOs and vice versa to facilitate data transfer without revealing internal structures.

 * In this demo, all fields from the entity are mapped onto the corresponding DTOs.
 * However, in a real world applications, typically only a subset of fields would be mapped based on specific requirements.
 */

@Component
public class BookMapper {

    private final ModelMapper modelMapper;

    public BookMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Book mapRequestToEntity(final BookRequest request) {
        return modelMapper.map(request, Book.class);
    }

    public BookDTO mapEntityToDTO(final Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
