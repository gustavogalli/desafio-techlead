package com.techlead.library.service;

import com.techlead.library.domain.Librarian;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibrarianService {

    List<Librarian> findAll();

    Librarian findById(Integer id);

    Librarian findByEmail(String email);
}
