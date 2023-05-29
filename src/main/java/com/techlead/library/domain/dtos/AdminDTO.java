package com.techlead.library.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private Integer id;

    @NotNull(message = "EMAIL is required.")
    private String email;

    @NotNull(message = "PASSWORD is required.")
    private String password;

    private String type;

//    @NotNull(message = "UPLOADED BOOKS is required.")
    private List<Integer> uploadedBooks;

}
