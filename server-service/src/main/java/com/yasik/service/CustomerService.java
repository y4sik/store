package com.yasik.service;


import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;
import com.yasik.service.excetpion.handle.EntityNotFoundException;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(GraphType graphType);

    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    Customer getCustomer(long id, GraphType graphType) throws EntityNotFoundException;

    void deleteCustomer(Customer customer);
}
