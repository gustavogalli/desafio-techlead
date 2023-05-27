package com.techlead.library.repository;

import com.techlead.library.domain.Book;
import com.techlead.library.domain.dtos.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByIsbn(String isbn);
}
