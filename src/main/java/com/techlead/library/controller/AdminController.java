package com.techlead.library.controller;

import com.techlead.library.domain.dtos.AdminDTO;
import com.techlead.library.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    public static final String ID = "/{id}";

    private AdminService service;
    private ModelMapper mapper;

    public AdminController(AdminService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(ID)
    public ResponseEntity<AdminDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), AdminDTO.class));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdminDTO> findByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(mapper.map(service.findByEmail(email), AdminDTO.class));
    }

}
