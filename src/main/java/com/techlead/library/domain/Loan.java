package com.techlead.library.domain;

import com.techlead.library.domain.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "l05_loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer book;
    private Integer customer;
    private Integer loanDays;
    private boolean loanApproved;
    private Integer status;
    private LocalDate startDate;
    private LocalDate endDate;

    public Loan(Integer id, Integer book, Integer customer, Integer loanDays, boolean loanApproved, Integer status) {
        this.id = id;
        this.book = book;
        this.customer = customer;
        this.loanDays = loanDays;
        this.loanApproved = loanApproved;
        this.status = Status.OPEN.getCode();
    }
}
