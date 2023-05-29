package com.techlead.library.service;

import com.techlead.library.domain.Admin;
import com.techlead.library.domain.dtos.AdminDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    List<Admin> findAll();

    Admin findById(Integer id);

    Admin findByCpf(String cpf);

    Admin findByEmail(String email);

    Admin create(AdminDTO dto);

    Admin update(AdminDTO dto);

    void delete(Integer id);
}
