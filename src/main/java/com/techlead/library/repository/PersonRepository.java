package com.techlead.library.repository;

import com.techlead.library.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByCpf(String cpf);
    Optional<Person> findByEmail(String email);

}
