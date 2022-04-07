package ru.learnup.march.bookstore.services;

import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.entity.Customer;
import ru.learnup.march.bookstore.repository.AuthorRepository;
import ru.learnup.march.bookstore.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return repository.getById(id);
    }

    public Customer getCustomerByName(String name) {
        return repository.getCustomerByName(name);
    }
}
