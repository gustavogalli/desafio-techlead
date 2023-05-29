package com.techlead.library.service;

import com.techlead.library.domain.Admin;
import com.techlead.library.domain.Book;
import com.techlead.library.domain.Customer;
import com.techlead.library.domain.Librarian;
import com.techlead.library.repository.AdminRepository;
import com.techlead.library.repository.BookRepository;
import com.techlead.library.repository.CustomerRepository;
import com.techlead.library.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void startsDB(){
        Admin admin = new Admin(1, "Admin", null, "admin@library.com", encoder.encode("admin"), "Admin", null, null);
        adminRepository.save(admin);

        Librarian librarian = new Librarian(1, "Librarian", null, "librarian@library.com", encoder.encode("librarian"), "Librarian", null, null);
        librarianRepository.save(librarian);

//        Customer cust1 = new Customer(null, "John", "1234567810", "john@mail.com", encoder.encode("123"), "Customer", null, null);
//        customerRepository.saveAll(Arrays.asList(cust1));

    }
}
