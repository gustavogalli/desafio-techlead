package com.techlead.library.service.impl;

import com.techlead.library.domain.Librarian;
import com.techlead.library.repository.LibrarianRepository;
import com.techlead.library.repository.PersonRepository;
import com.techlead.library.service.LibrarianService;
import com.techlead.library.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    private LibrarianRepository repository;
    private PersonRepository personRepository;
    private ModelMapper mapper;
    private BCryptPasswordEncoder encoder;

    public LibrarianServiceImpl(LibrarianRepository repository, PersonRepository personRepository, ModelMapper mapper, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public List<Librarian> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Librarian findById(Integer id) {
        Optional<Librarian> foundAdmin = this.repository.findById(id);
        return foundAdmin.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    @Override
    public Librarian findByEmail(String email){
        Optional<Librarian> foundAdmin = this.repository.findByEmail(email);
        return foundAdmin.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

}
