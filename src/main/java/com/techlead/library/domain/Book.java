package com.techlead.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "l01_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String isbn;

    private String title;
    private String author;
    private String publisher;
    private Integer pageQty;
    private boolean borrowed;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer uploader;

    @ManyToOne
    @JoinColumn(name = "customer_borrower_id")
    private Customer borrower;


}
