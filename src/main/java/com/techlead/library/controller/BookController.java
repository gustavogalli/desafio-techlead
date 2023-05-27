package com.techlead.library.controller;

import com.techlead.library.domain.Book;
import com.techlead.library.domain.dtos.BookDTO;
import com.techlead.library.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    public static final String ID = "/{id}";

    private BookService service;
    private ModelMapper mapper;

    public BookController(BookService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok().body(this.service.findAll()
                .stream().map(book -> mapper.map(book, BookDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping(ID)
    public ResponseEntity<BookDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), BookDTO.class));
    }

    @PostMapping
    public ResponseEntity<BookDTO> upload(@RequestBody BookDTO dto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path(ID).buildAndExpand(service.upload(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<BookDTO> update(@PathVariable Integer id, @RequestBody BookDTO dto){
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(dto), BookDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<BookDTO> delete(@PathVariable Integer id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
