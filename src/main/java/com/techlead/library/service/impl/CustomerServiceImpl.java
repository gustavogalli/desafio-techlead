package com.techlead.library.service.impl;

import com.techlead.library.domain.Customer;
import com.techlead.library.domain.dtos.CustomerDTO;
import com.techlead.library.repository.CustomerRepository;
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
    private ModelMapper mapper;
    private BCryptPasswordEncoder encoder;

    public CustomerServiceImpl(CustomerRepository repository, ModelMapper mapper, BCryptPasswordEncoder encoder) {
        this.repository = repository;
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
        findByCpf(dto);
        dto.setPassword(encoder.encode(dto.getPassword()));
        return this.repository.save(mapper.map(dto, Customer.class));
    }

    @Override
    public Customer update(CustomerDTO dto) {
        findByCpf(dto);
        return this.repository.save(mapper.map(dto, Customer.class));
    }

    @Override
    public void delete(Integer id) {

    }

    private void findByCpf(CustomerDTO dto){
        Optional<Customer> foundCustomer = this.repository.findByCpf(dto.getCpf());

        if(foundCustomer.isPresent() && !foundCustomer.get().getId().equals(dto.getCpf())){
            throw new DataIntegrityViolationException("Customer already registered.");
        }
    }
}
