package ru.learnup.march.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnup.march.bookstore.entity.Author;
import ru.learnup.march.bookstore.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer getCustomerByName(String name);
}
