package com.techlead.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "l03_customer")
public class Customer extends Person {

    public Customer(){
        super();
    }

    public Customer(Integer id, String name, String cpf, String email, String password, String type, List<Integer> uploadedBooks, List<Integer> borrowedBooks) {
        super(id, name, cpf, email, password, type);
        this.uploadedBooks = uploadedBooks;
        this.borrowedBooks = borrowedBooks;
    }

//    @JsonIgnore
//    @OneToMany(mappedBy = "uploader")
    private List<Integer> uploadedBooks;

//    @JsonIgnore
//    @OneToMany(mappedBy = "borrower")
    private List<Integer> borrowedBooks;

}
