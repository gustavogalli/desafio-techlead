package com.techlead.library.domain;

import com.techlead.library.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    private Integer status;

    private Book(){

    }

    public Book(Integer id, String isbn, String title, String author, String publisher, Integer pageQty, Integer status, Integer uploader, Integer borrower) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pageQty = pageQty;
        this.status = Status.AVAILABLE.getCode();
        this.uploader = uploader;
        this.borrower = borrower;
    }

    //    @ManyToOne
//    @JoinColumn(name = "customer_id")
    private Integer uploader;

//    @ManyToOne
//    @JoinColumn(name = "customer_borrower_id")
    private Integer borrower;


}
