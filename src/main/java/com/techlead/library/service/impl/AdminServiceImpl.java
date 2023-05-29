package com.techlead.library.service.impl;

import com.techlead.library.domain.Admin;
import com.techlead.library.repository.AdminRepository;
import com.techlead.library.repository.PersonRepository;
import com.techlead.library.service.AdminService;
import com.techlead.library.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository repository;
    private PersonRepository personRepository;
    private ModelMapper mapper;
    private BCryptPasswordEncoder encoder;

    public AdminServiceImpl(AdminRepository repository, PersonRepository personRepository, ModelMapper mapper, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public List<Admin> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Admin findById(Integer id) {
        Optional<Admin> foundAdmin = this.repository.findById(id);
        return foundAdmin.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    @Override
    public Admin findByEmail(String email){
        Optional<Admin> foundAdmin = this.repository.findByEmail(email);
        return foundAdmin.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

}
