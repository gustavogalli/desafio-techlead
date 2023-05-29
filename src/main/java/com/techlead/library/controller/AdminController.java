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
    @GetMapping
    public ResponseEntity<List<AdminDTO>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll()
                .stream().map(customer -> mapper.map(customer, AdminDTO.class)).collect(Collectors.toList()));
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(ID)
    public ResponseEntity<AdminDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), AdminDTO.class));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AdminDTO> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok().body(mapper.map(service.findByCpf(cpf), AdminDTO.class));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdminDTO> findByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(mapper.map(service.findByEmail(email), AdminDTO.class));
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AdminDTO> create(@Valid @RequestBody AdminDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(ID)
    public ResponseEntity<AdminDTO> update(@PathVariable Integer id, @Valid @RequestBody AdminDTO dto){
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto), AdminDTO.class));
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(ID)
    public ResponseEntity<AdminDTO> delete(@PathVariable Integer id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
