package com.techlead.library.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Integer id;

    @NotNull(message = "ISBN is required.")
    private String isbn;

    @NotNull(message = "TITLE is required.")
    private String title;

    @NotNull(message = "AUTHOR is required.")
    private String author;

    @NotNull(message = "PUBLISHER is required.")
    private String publisher;

    @NotNull(message = "PAGEQTY is required.")
    private Integer pageQty;

}