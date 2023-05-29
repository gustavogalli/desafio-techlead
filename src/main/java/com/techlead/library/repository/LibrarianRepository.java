package com.techlead.library.repository;

import com.techlead.library.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);
}
