package com.techlead.library.service;

import com.techlead.library.domain.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibrarianService {

    List<Admin> findAll();

    Admin findById(Integer id);

    Admin findByEmail(String email);
}
