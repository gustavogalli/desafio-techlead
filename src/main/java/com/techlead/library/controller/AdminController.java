package com.techlead.library.controller;

import com.techlead.library.domain.dtos.AdminDTO;
import com.techlead.library.service.AdminService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class AdminController {
    public static final String ID = "/{id}";

    private AdminService service;
    private ModelMapper mapper;

    public AdminController(AdminService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(ID)
    public ResponseEntity<AdminDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), AdminDTO.class));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdminDTO> findByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(mapper.map(service.findByEmail(email), AdminDTO.class));
    }

}
