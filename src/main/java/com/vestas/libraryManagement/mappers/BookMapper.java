package com.vestas.libraryManagement.mappers;

import com.vestas.libraryManagement.dtos.request.CreateBookRequest;
import com.vestas.libraryManagement.dtos.response.BookDTO;
import com.vestas.libraryManagement.entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class is designed to prevent direct exposure of database entity objects to external systems.
 * It converts entity objects to DTOs and vice versa to facilitate data transfer without revealing internal structures.

 * In this demo, all fields from the entity are mapped onto the corresponding DTOs.
 * However, in a production application, typically only a subset of fields would be mapped based on specific requirements.
 */

@Component
public class BookMapper {

    private final ModelMapper modelMapper;

    public BookMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Book mapRequestToEntity(final CreateBookRequest request) {
        return modelMapper.map(request, Book.class);
    }

    public BookDTO mapEntityToDTO(final Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book mapDTOToEntity(final BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}
