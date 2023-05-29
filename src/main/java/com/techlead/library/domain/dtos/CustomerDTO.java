package com.techlead.library.domain.dtos;

import com.techlead.library.domain.Book;
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
public class CustomerDTO {

    private Integer id;

    @NotNull(message = "NAME is required.")
    private String name;

    @NotNull(message = "CPF is required.")
    private String cpf;

//    @NotNull(message = "PROFILE is required.")
//    private Integer profile;

    @NotNull(message = "EMAIL is required.")
    private String email;

    @NotNull(message = "PASSWORD is required.")
    private String password;

    private String type;

//    @NotNull(message = "UPLOADED BOOKS is required.")
    private List<Integer> uploadedBooks;

//    @NotNull(message = "BORROWED BOOKS is required.")
    private List<Integer> borrowedBooks;
}
