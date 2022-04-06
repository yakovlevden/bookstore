package ru.learnup.march.bookstore.services;

import org.springframework.stereotype.Service;
import ru.learnup.march.bookstore.entity.Customer;
import ru.learnup.march.bookstore.entity.OrderDetail;
import ru.learnup.march.bookstore.repository.CustomerRepository;
import ru.learnup.march.bookstore.repository.OrderDetailRepository;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository repository;

    public OrderDetailService(OrderDetailRepository repository) {
        this.repository = repository;
    }

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return repository.save(orderDetail);
    }

    public List<OrderDetail> getOrderDetails() {
        return repository.findAll();
    }

    public OrderDetail getOrderDetailById(Long id) {
        return repository.getById(id);
    }

}
