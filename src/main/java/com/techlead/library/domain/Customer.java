package com.techlead.library.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    private List<Integer> uploadedBooks;

    private List<Integer> borrowedBooks;

    private Integer daysOfPenalty;

}
