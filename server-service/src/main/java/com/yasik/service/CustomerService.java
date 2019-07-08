package com.yasik.service;


import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(GraphType graphType);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer, Customer currentCustomer);

    Customer getCustomer(long id, GraphType graphType);

    long deleteCustomer(long id);
}
