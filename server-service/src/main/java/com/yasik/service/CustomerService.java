package com.yasik.service;


import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(GraphType graphType);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    Customer getCustomer(long id, GraphType graphType);

    void deleteCustomer(Customer customer);
}
