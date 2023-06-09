package com.techlead.library.controller;

import com.techlead.library.domain.dtos.CustomerDTO;
import com.techlead.library.service.CustomerService;
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
public class CustomerController {
    public static final String ID = "/{id}";

    private CustomerService service;
    private ModelMapper mapper;

    public CustomerController(CustomerService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll()
                .stream().map(customer -> mapper.map(customer, CustomerDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping(ID)
    public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), CustomerDTO.class));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<CustomerDTO> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok().body(mapper.map(service.findByCpf(cpf), CustomerDTO.class));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> findByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(mapper.map(service.findByEmail(email), CustomerDTO.class));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(ID)
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO dto){
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto), CustomerDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<CustomerDTO> delete(@PathVariable Integer id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
