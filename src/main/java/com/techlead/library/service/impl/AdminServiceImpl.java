package com.techlead.library.service.impl;

import com.techlead.library.domain.Admin;
import com.techlead.library.domain.Admin;
import com.techlead.library.domain.Person;
import com.techlead.library.domain.dtos.AdminDTO;
import com.techlead.library.repository.AdminRepository;
import com.techlead.library.repository.AdminRepository;
import com.techlead.library.repository.PersonRepository;
import com.techlead.library.service.AdminService;
import com.techlead.library.service.exceptions.DataIntegrityViolationException;
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
    public Admin findByCpf(String cpf){
        Optional<Admin> foundAdmin = this.repository.findByCpf(cpf);
        return foundAdmin.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    @Override
    public Admin findByEmail(String email){
        Optional<Admin> foundAdmin = this.repository.findByEmail(email);
        return foundAdmin.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    @Override
    public Admin create(AdminDTO dto) {
        dto.setId(null);
        dto.setType("Admin");
        dto.setPassword(encoder.encode(dto.getPassword()));
        validateCpfAndEmail(dto);
        return repository.save(mapper.map(dto, Admin.class));
    }

    @Override
    public Admin update(AdminDTO dto) {
        findByCpf(dto);
        dto.setPassword(encoder.encode(dto.getPassword()));
        return this.repository.save(mapper.map(dto, Admin.class));
    }

    @Override
    public void delete(Integer id) {
        Optional<Admin> foundAdmin = this.repository.findById(id);

        if(foundAdmin.get().getBorrowedBooks().size() > 0){
            throw new DataIntegrityViolationException("Admin has borrowed books and cannot be deleted.");
        }
        this.repository.deleteById(id);
    }

    private void findByCpf(AdminDTO dto){
        Optional<Admin> foundAdmin = this.repository.findByCpf(dto.getCpf());

        if(foundAdmin.isPresent() && !foundAdmin.get().getId().equals(dto.getId())){
            throw new DataIntegrityViolationException("Admin already registered.");
        }
    }

    private void validateCpfAndEmail(AdminDTO dto){
        Optional<Person> obj = personRepository.findByCpf(dto.getCpf());

        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("CPF already registered.");
        }

        obj = personRepository.findByEmail(dto.getEmail());

        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("Email already registered");
        }
    }
}
