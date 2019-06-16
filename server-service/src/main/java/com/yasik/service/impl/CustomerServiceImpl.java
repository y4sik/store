package com.yasik.service.impl;

import com.yasik.dao.CustomerDAO;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers(GraphType graphType) {
        return customerDAO.getAll(graphType);
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.persist(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        customerDAO.merge(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(long id, GraphType graphType) {
        return customerDAO.geById(id, graphType);
    }

    @Override
    @Transactional
    public void deleteCustomer(Customer customer) {
        customerDAO.remove(customer);
    }
}
