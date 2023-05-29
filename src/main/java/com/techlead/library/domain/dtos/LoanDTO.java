package com.techlead.library.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {

    private Integer id;
    private Integer book;
    private Integer customer;
    private Integer loanDays;
    private boolean loanApproved;
    private Integer status;
    private LocalDate startDate;
    private LocalDate endDate;

}
