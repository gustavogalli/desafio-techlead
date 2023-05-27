package com.techlead.library.domain.dtos;

import jakarta.validation.constraints.NotNull;

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
}
