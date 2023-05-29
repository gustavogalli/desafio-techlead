package com.techlead.library.service.impl;

import com.techlead.library.domain.Loan;
import com.techlead.library.domain.dtos.LoanDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {

    List<Loan> findAll();

    Loan findById(Integer id);

    Loan update(LoanDTO dto);

    Loan create(LoanDTO dto);

    void delete(Integer id);
}
