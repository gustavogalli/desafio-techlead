package com.techlead.library.service;

import com.techlead.library.domain.Book;
import com.techlead.library.domain.dtos.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> findAll();

    Book findById(Integer id);

    Book upload(BookDTO dto);

    Book update(BookDTO dto);

    void delete(Integer id);

}
