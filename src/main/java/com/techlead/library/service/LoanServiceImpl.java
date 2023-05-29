package com.techlead.library.service;

import com.techlead.library.domain.Book;
import com.techlead.library.domain.Loan;
import com.techlead.library.domain.dtos.LoanDTO;
import com.techlead.library.repository.LoanRepository;
import com.techlead.library.service.exceptions.DataIntegrityViolationException;
import com.techlead.library.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository repository;

    private ModelMapper mapper;

    public LoanServiceImpl(LoanRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Loan> findAll(){
        return this.repository.findAll();
    }

    @Override
    public Loan findById(Integer id) {
        Optional<Loan> foundLoan = this.repository.findById(id);
        return foundLoan.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    @Override
    public Loan create(LoanDTO dto) {
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(dto.getStartDate().plusDays(dto.getLoanDays()));
        return this.repository.save(mapper.map(dto, Loan.class));
    }

    @Override
    public Loan update(LoanDTO dto) {
        return this.repository.save(mapper.map(dto, Loan.class));
    }

    @Override
    public void delete(Integer id) {
        Optional<Loan> foundLoan = this.repository.findById(id);

        if(foundLoan.isPresent() && foundLoan.get().getStatus() == 8){
            throw new DataIntegrityViolationException("The loan is open and cannot be deleted.");
        }

        this.repository.deleteById(id);
    }
}
