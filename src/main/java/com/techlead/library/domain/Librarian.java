package com.techlead.library.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "l99_admin")
public class Librarian extends Person {

    public Librarian(){
        super();
    }

    public Librarian(Integer id, String name, String cpf, String email, String password, String type, List<Integer> uploadedBooks, List<Integer> borrowedBooks) {
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
