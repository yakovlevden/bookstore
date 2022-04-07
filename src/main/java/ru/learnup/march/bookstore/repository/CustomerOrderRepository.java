package ru.learnup.march.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnup.march.bookstore.entity.Customer;
import ru.learnup.march.bookstore.entity.CustomerOrder;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    List<CustomerOrder> findAllCustomerOrdersByCustomer(Customer customer);
}
