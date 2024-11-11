package com.vestas.libraryManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateBookRequest {
    private String isbn;
    private String title;
    private BigDecimal price;
    private String author;
}
