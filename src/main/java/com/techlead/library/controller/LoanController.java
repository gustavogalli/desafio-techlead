package com.techlead.library.controller;

import com.techlead.library.domain.Loan;
import com.techlead.library.domain.dtos.BookDTO;
import com.techlead.library.domain.dtos.LoanDTO;
import com.techlead.library.service.LoanService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loan")
public class LoanController {

    public static final String ID = "/id";

    private LoanService service;
    private ModelMapper mapper;

    public LoanController(LoanService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll()
                .stream().map(loan -> mapper.map(loan, LoanDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping(ID)
    public ResponseEntity<LoanDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), LoanDTO.class));
    }

    @PostMapping
    public ResponseEntity<LoanDTO> create(@Valid @RequestBody LoanDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path(ID).buildAndExpand(service.create(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(ID)
    public ResponseEntity<LoanDTO> update(@PathVariable Integer id, @Valid @RequestBody LoanDTO dto){
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto), LoanDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<BookDTO> delete(@PathVariable Integer id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
