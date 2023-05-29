package com.techlead.library.repository;

import com.techlead.library.domain.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    Optional<Librarian> findByEmail(String email);
}
