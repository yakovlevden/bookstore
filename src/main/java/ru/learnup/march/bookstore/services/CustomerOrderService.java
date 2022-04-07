package ru.learnup.march.bookstore.services;

import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.*;
import ru.learnup.march.bookstore.repository.CustomerOrderRepository;
import ru.learnup.march.bookstore.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository repository;

    public CustomerOrderService(CustomerOrderRepository repository) {
        this.repository = repository;
    }

    public CustomerOrder createCustomerOrder(Customer customer, OrderDetail orderDetail) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(orderDetail.getId());
        customerOrder.setCustomer(customer);
        customerOrder.setSum(orderDetail.getPrice() * orderDetail.getAmount());
        return repository.save(customerOrder);
    }

    public List<CustomerOrder> getCustomerOrders() {
        return repository.findAll();
    }

    public CustomerOrder getCustomerOrderById(Long id) {
        return repository.getById(id);
    }

    public List<CustomerOrder> getCustomerOrdersByCustomer(Customer customer) {
        return repository.findAllCustomerOrdersByCustomer(customer);
    }
}
