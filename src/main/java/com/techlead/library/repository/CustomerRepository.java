package com.techlead.library.repository;

import com.techlead.library.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCpf(String cpf);

    Optional<Customer> findByEmail(String email);
}
