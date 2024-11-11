package com.vestas.libraryManagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    @NotNull
    private String title;
    private BigDecimal price;
    private String author;
}
