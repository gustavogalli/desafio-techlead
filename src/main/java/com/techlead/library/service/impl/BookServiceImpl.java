package com.techlead.library.service.impl;

import com.techlead.library.domain.Book;
import com.techlead.library.domain.dtos.BookDTO;
import com.techlead.library.repository.BookRepository;
import com.techlead.library.service.BookService;
import com.techlead.library.service.exceptions.DataIntegrityViolationException;
import com.techlead.library.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private ModelMapper mapper;

    public BookServiceImpl(BookRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Book> findAll(){
        return this.repository.findAll();
    }

    @Override
    public Book findById(Integer id){
        Optional<Book> foundBook = this.repository.findById(id);
        return foundBook.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    @Override
    public Book create(BookDTO dto){
        findByIsbn(dto);
        return this.repository.save(mapper.map(dto, Book.class));
    }

    @Override
    public Book update(BookDTO dto){
        findByIsbn(dto);
        return this.repository.save(mapper.map(dto, Book.class));
    }

    @Override
    public void delete(Integer id){
        Optional<Book> foundBook = this.repository.findById(id);

        if(foundBook.get().isBorrowed()){
            throw new DataIntegrityViolationException("The book is borrowed and cannot be deleted.");
        }
        this.repository.deleteById(id);
    }

    private void findByIsbn(BookDTO dto){
        Optional<Book> foundBook = this.repository.findByIsbn(dto.getIsbn());

        if(foundBook.isPresent() && !foundBook.get().getId().equals(dto.getIsbn())){
            throw new DataIntegrityViolationException("Book already registered.");
        }
    }

}
