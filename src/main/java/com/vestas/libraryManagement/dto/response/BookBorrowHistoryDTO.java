package com.vestas.libraryManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// TODO: use records instead, records are not supported by modelMapper so try other mapping tools
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookBorrowHistoryDTO {
    private Long id;
    private Long userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
