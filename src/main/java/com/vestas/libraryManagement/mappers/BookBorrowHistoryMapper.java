package com.vestas.libraryManagement.mappers;

import com.vestas.libraryManagement.dtos.response.BookBorrowHistoryDTO;
import com.vestas.libraryManagement.entities.BookBorrowHistory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class is designed to prevent direct exposure of database entity objects to external systems.
 * It converts entity objects to DTOs and vice versa to facilitate data transfer without revealing internal structures.

 * In this demo, all fields from the entity are mapped onto the corresponding DTOs.
 * However, in a production application, typically only a subset of fields would be mapped based on specific requirements.
 */

@Component
public class BookBorrowHistoryMapper {

    private final ModelMapper modelMapper;

    public BookBorrowHistoryMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookBorrowHistoryDTO mapEntityToDTO(final BookBorrowHistory bookBorrowHistory) {
        return modelMapper.map(bookBorrowHistory, BookBorrowHistoryDTO.class);
    }

    public BookBorrowHistory mapDTOToEntity(final BookBorrowHistoryDTO bookBorrowHistoryDTO) {
        return modelMapper.map(bookBorrowHistoryDTO, BookBorrowHistory.class);
    }
}
