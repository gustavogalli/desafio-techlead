package com.techlead.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "l02_customer")
public class Customer extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "uploader")
    private List<Book> uploadedBooks;

    @JsonIgnore
    @OneToMany(mappedBy = "borrower")
    private List<Book> borrowedBooks;

}
