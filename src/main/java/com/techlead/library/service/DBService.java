package com.techlead.library.service;

import com.techlead.library.domain.Book;
import com.techlead.library.domain.Customer;
import com.techlead.library.domain.enums.Profile;
import com.techlead.library.repository.BookRepository;
import com.techlead.library.repository.CustomerRepository;
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
    private BCryptPasswordEncoder encoder;

    public void startsDB(){
        Book book1 = new Book(null, "ISBN 1234", "A mosca", "Franz Kafka",
                "Editora A", 200, false, null, null);

        Book book2 = new Book(null, "ISBN 6542", "Fogo no céu", "Travis Walton",
                "Editora B", 310, false, null, null);

        Book book3 = new Book(null, "ISBN 4512", "A ciranda", "John Kash",
                "Editora C", 154, false, null, null);

        // id name cpf email password profiles uploadedBooks borrowedBooks
        Customer cust1 = new Customer(null, "John", "1234567810", "john@mail.com",
                encoder.encode("123"), null, null);
        cust1.addProfile(Profile.ADMIN);

        Customer cust2 = new Customer(null, "Mary", "65432198745", "mary@mail.com",
                encoder.encode("123"), null, null);
        cust1.addProfile(Profile.ADMIN);

        Customer cust3 = new Customer(null, "Peter", "87542145124", "peter@mail.com",
                encoder.encode("123"), null, null);
        cust1.addProfile(Profile.ADMIN);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3));
        customerRepository.saveAll(Arrays.asList(cust1, cust2, cust3));
    }
}
