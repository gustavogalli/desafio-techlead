package com.techlead.library.controller;

import com.techlead.library.domain.Customer;
import com.techlead.library.domain.dtos.CustomerDTO;
import com.techlead.library.service.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll()
                .stream().map(book -> mapper.map(book, CustomerDTO.class)).collect(Collectors.toList()));
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(ID)
    public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), CustomerDTO.class));
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO dto) {
        Customer newObj = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(ID)
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO dto){
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto), CustomerDTO.class));
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(ID)
    public ResponseEntity<CustomerDTO> delete(@PathVariable Integer id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
