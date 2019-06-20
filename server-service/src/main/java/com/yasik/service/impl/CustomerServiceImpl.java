package com.yasik.service.impl;

import com.yasik.dao.CustomerDAO;
import com.yasik.model.entity.customer.Authorities;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CustomerService;
import com.yasik.service.excetpion.handle.EntityNotFoundException;
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
        List<Customer> customers = customerDAO.getAll(graphType);
        if ((customers.size() == 0)) {
            throw new EntityNotFoundException("Customers not found");
        }
        return customerDAO.getAll(graphType);
    }

    @Override
    @Transactional
    public void createCustomer(Customer customer) {
        Authorities authority = new Authorities("CUSTOMER");//must be table with authorities;
        customer.add(authority);
        customerDAO.persist(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        customerDAO.merge(customer);
    }

    @Override
    @Transactional
    public Customer getCustomer(long id, GraphType graphType) throws EntityNotFoundException {
        Customer customer = customerDAO.geById(id, graphType);
        if (customer == null) {
            throw new EntityNotFoundException("Customer with id " + id + ", not found");
        }
        return customer;
    }

    @Override
    @Transactional
    public void deleteCustomer(Customer customer) {
        customerDAO.remove(customer);
    }
}
