package com.vestas.libraryManagement.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequest {
    @NotBlank
    @NotNull
    private String isbn;
    @NotBlank
    @NotNull
    private String title;
    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private BigDecimal price;
    @NotBlank
    @NotNull
    private String author;
}
