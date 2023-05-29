package com.techlead.library.service;

import com.techlead.library.domain.Customer;
import com.techlead.library.domain.dtos.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> findAll();

    Customer findById(Integer id);

    Customer findByCpf(String cpf);

    Customer findByEmail(String email);

    Customer create(CustomerDTO dto);

    Customer update(CustomerDTO dto);

    void delete(Integer id);
}
