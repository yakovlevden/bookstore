package ru.learnup.march.bookstore.services;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.march.bookstore.entity.Book;
import ru.learnup.march.bookstore.entity.Customer;
import ru.learnup.march.bookstore.entity.CustomerOrder;
import ru.learnup.march.bookstore.entity.OrderDetail;
import ru.learnup.march.bookstore.repository.BookStorageRepository;
import ru.learnup.march.bookstore.repository.CustomerRepository;
import ru.learnup.march.bookstore.repository.OrderDetailRepository;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository repository;
    private final ConfigurableApplicationContext context;

    public OrderDetailService(OrderDetailRepository repository, ConfigurableApplicationContext context) {
        this.repository = repository;
        this.context = context;
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

    @Transactional
    public OrderDetail buyBook(Customer customer, Book book, int amount) throws Exception {
        OrderDetail orderDetail = new OrderDetail(book, amount, book.getPrice());
        orderDetail = createOrderDetail(orderDetail);

        BookStorageService bookStorageService = context.getBean(BookStorageService.class);
        bookStorageService.takeBook(book, amount);

        CustomerOrderService customerOrderService = context.getBean(CustomerOrderService.class);
        CustomerOrder customerOrder = customerOrderService.createCustomerOrder(customer, orderDetail);

        return orderDetail;
    }

}
