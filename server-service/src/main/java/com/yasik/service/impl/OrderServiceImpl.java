package com.yasik.service.impl;

import com.yasik.dao.CustomerDAO;
import com.yasik.dao.OrderDAO;
import com.yasik.dao.ProductDAO;
import com.yasik.model.entity.Order;
import com.yasik.model.entity.customer.Address;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;
import com.yasik.service.OrderService;
import com.yasik.service.exception.EmptyObjectException;
import com.yasik.service.exception.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Order> getOrders(GraphType graphType) {
        List<Order> orders = orderDAO.getAll(graphType);
        if (orders.size() == 0) {
            throw new EntityNotFoundException("There are no Orders!");
        }
        return orders;
    }


    @Override
    @Transactional
    public Order orderProduct(Order order, Customer customer) {
//        Customer customer = customerDAO.getById(customerId, GraphType.CUSTOMER_WITH_ADDRESSES);
        Set<Address> addresses = customer.getAddresses();
        if (addresses.size() == 0) {
            throw new EmptyObjectException("Customer with id [" + customer.getId() + "]," +
                    " has no Address");
        }
        if (addresses.size() == 1) {
            order.setAddress(addresses.stream().findFirst().get());
        }
        if (order.getAddress() == null) {
            throw new EmptyObjectException("Order with id [" + order.getId() + "]," +
                    " doesn't contain Address");
        }
        List<Long> productIDs = order.getProducts().stream().map(product -> product.getId())
                .collect(Collectors.toList());
        Set<Product> products = productDAO.getProductsByIdList(productIDs).stream()
                .collect(Collectors.toSet());
        if (products.size() == 0) {
            throw new EntityNotFoundException("There are no products with IDs :" + productIDs);
        }
        Customer newCustomer = new Customer();
        newCustomer.setId(customer.getId());
        order.setCustomer(newCustomer);
        order.setProducts(products);
        return orderDAO.persist(order);
    }

    @Override
    @Transactional
    public List<Order> getCustomerOrders(long customerId) {
        Customer customer = customerDAO.getById(customerId, GraphType.PURE_ENTITY);
        if (customer == null) {//for admin
            throw new EntityNotFoundException("Unable to get Customer Orders. " +
                    "No Customer with id [" + customerId + "]!");
        }
        List<Order> orders = orderDAO.getOrdersByCustomerId(customerId);
        if (orders.size() == 0) {
            throw new EntityNotFoundException("Customer with id [" + customerId + "] has no Orders!");
        }
        return orders;
    }

    @Override
    @Transactional
    public List<Order> getProductOrders(long productId) {
        Product product = productDAO.getById(productId, GraphType.PURE_ENTITY);
        if (product == null) {
            throw new EntityNotFoundException("Unable to get Orders. " +
                    "No Product with id [" + product + "]!");
        }
        List<Order> orders = orderDAO.getOrdersByProductId(productId);
        if (orders.size() == 0) {
            throw new EntityNotFoundException("Product with id [" + productId + "] has no Orders!");
        }
        return orders;
    }
}
