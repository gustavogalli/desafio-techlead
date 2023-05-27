package com.techlead.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "l02_person")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String cpf;

    private Integer profile;

    @Column(unique = true)
    private String email;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "uploader")
    private Set<Book> uploadedBooks = new HashSet<>();
}