package com.yasik.web.controller;

import com.yasik.model.entity.Order;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CustomerService;
import com.yasik.service.OrderService;
import com.yasik.web.annotation.CurrentCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/orders")
    public Order makeOrder(@RequestBody Order order, @CurrentCustomer Customer customer) {
        Customer customerWithAddress =
                customerService.getCustomer(customer.getId(), GraphType.CUSTOMER_WITH_ADDRESSES);
        Order newOrder = orderService.orderProduct(order, customerWithAddress);
        LOGGER.info("Order was successfully completed, id [" + newOrder.getId() + "]!");
        return newOrder;
    }

    @GetMapping("/customer_orders")
    public List<Order> getCustomerOrders(@CurrentCustomer Customer customer) {
        return orderService.getCustomerOrders(customer.getId());

    }

    @GetMapping("/product_orders/{productId}")
    public List<Order> getProductOrders(@PathVariable long productId) {
        return orderService.getProductOrders(productId);
    }


}
