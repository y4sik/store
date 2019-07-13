package com.yasik.service;

import com.yasik.model.entity.Order;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;

import java.util.List;

public interface OrderService {

    List<Order> getOrders(GraphType graphType);

    Order orderProduct(Order order, Customer customer);

    List<Order> getCustomerOrders(long customerId);

    List<Order> getProductOrders(long productId);

}
