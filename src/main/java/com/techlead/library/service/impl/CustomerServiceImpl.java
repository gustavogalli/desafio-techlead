package com.techlead.library.service.impl;

import com.techlead.library.domain.Customer;
import com.techlead.library.domain.Person;
import com.techlead.library.domain.dtos.CustomerDTO;
import com.techlead.library.repository.CustomerRepository;
import com.techlead.library.repository.PersonRepository;
import com.techlead.library.service.CustomerService;
import com.techlead.library.service.exceptions.DataIntegrityViolationException;
import com.techlead.library.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;
    private PersonRepository personRepository;
    private ModelMapper mapper;
    private BCryptPasswordEncoder encoder;

    public CustomerServiceImpl(CustomerRepository repository, PersonRepository personRepository, ModelMapper mapper, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public List<Customer> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> foundCustomer = this.repository.findById(id);
        return foundCustomer.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    @Override
    public Customer create(CustomerDTO dto) {
        dto.setId(null);
        dto.setPassword(encoder.encode(dto.getPassword()));
        validateCpfAndEmail(dto);
        return repository.save(mapper.map(dto, Customer.class));
    }

    @Override
    public Customer update(CustomerDTO dto) {
        findByCpf(dto);
        return this.repository.save(mapper.map(dto, Customer.class));
    }

    @Override
    public void delete(Integer id) {
        Optional<Customer> foundCustomer = this.repository.findById(id);

        if(foundCustomer.get().getBorrowedBooks().size() > 0){
            throw new DataIntegrityViolationException("Customer has borrowed books and cannot be deleted.");
        }
        this.repository.deleteById(id);
    }

    private void findByCpf(CustomerDTO dto){
        Optional<Customer> foundCustomer = this.repository.findByCpf(dto.getCpf());

        if(foundCustomer.isPresent() && !foundCustomer.get().getId().equals(dto.getCpf())){
            throw new DataIntegrityViolationException("Customer already registered.");
        }
    }

    private void validateCpfAndEmail(CustomerDTO dto){
        Optional<Person> obj = personRepository.findByCpf(dto.getCpf());

        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("CPF already registered.");
        }

        obj = personRepository.findByEmail(dto.getEmail());

        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("Email already registered");
        }
    }
}
