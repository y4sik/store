package com.yasik.service.impl;

import com.yasik.dao.CustomerDAO;
import com.yasik.model.entity.customer.Authorities;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.entity.customer.Role;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CustomerService;
import com.yasik.service.exception.EmailExistsException;
import com.yasik.service.exception.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    @Transactional
    public List<Customer> getCustomers(GraphType graphType) {
        List<Customer> customers = customerDAO.getAll(graphType);
        if ((customers.size() == 0)) {
//            LOGGER.error("There are no customers!");
            throw new EntityNotFoundException("There are no customers!");
        }
        return customers;
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        Authorities authority = new Authorities(Role.ROLE_CUSTOMER.toString());//must be table with roles;
        try {
            userDetailsService.loadUserByUsername(customer.getUsername());
        } catch (UsernameNotFoundException e) {
            Customer newCustomer = new Customer();
            newCustomer.add(authority);
            newCustomer.setUsername(customer.getUsername());
            newCustomer.setName(customer.getName());
            newCustomer.setSurname(customer.getSurname());
            newCustomer.setPhoneNumber(customer.getPhoneNumber());
            newCustomer.setGender(customer.getGender());
            newCustomer.setDateOfBirthday(customer.getDateOfBirthday());
            newCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
            return customerDAO.persist(newCustomer);

        }
//        LOGGER.error("An account [" + customer.getUsername() + "] already exists!");
        throw new EmailExistsException("An account [" + customer.getUsername() + "] already exists!");
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer, Customer currentCustomer) {
        if (currentCustomer == null) {
            throw new EntityNotFoundException("Can't update customer with id [" + customer.getId()
                    + "]. No such customer!");
        }
        if (customer.getUsername() == currentCustomer.getUsername() &&
                customer.getName() == currentCustomer.getName() &&
                customer.getSurname() == currentCustomer.getSurname() &&
                customer.getDateOfBirthday() == currentCustomer.getDateOfBirthday() &&
                customer.getGender() == currentCustomer.getGender() &&
                customer.getPhoneNumber() == currentCustomer.getPhoneNumber() &&
                passwordEncoder.encode(customer.getPassword()) == currentCustomer.getPassword()) {

        }
        currentCustomer.setName(customer.getName());
        currentCustomer.setSurname(customer.getSurname());
        currentCustomer.setPhoneNumber(customer.getPhoneNumber());
        currentCustomer.setGender(customer.getGender());
        currentCustomer.setDateOfBirthday(customer.getDateOfBirthday());
        currentCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
        try {
            userDetailsService.loadUserByUsername(customer.getUsername());
        } catch (UsernameNotFoundException e) {
            currentCustomer.setUsername(customer.getUsername());

            return customerDAO.merge(currentCustomer);
        }
        if (customer.getUsername() == currentCustomer.getUsername()) {
            return customerDAO.merge(currentCustomer);
        }
//        LOGGER.error("An account with such email [" + customer.getUsername() + "],  already exists!");
        throw new EmailExistsException("An account with such email [" + customer.getUsername() + "],  already exists!");
    }

    @Override
    @Transactional
    public Customer getCustomer(long id, GraphType graphType) {
        Customer customer = customerDAO.geById(id, graphType);
        if (customer == null) {
//            LOGGER.error("Customer with id [" + id + "], not found!");
            throw new EntityNotFoundException("Customer with id [" + id + "], not found!");
        }
        return customer;
    }

    @Override
    @Transactional
    public long deleteCustomer(long id) {
        Customer customer = customerDAO.geById(id, GraphType.PURE_ENTITY);
        if (customer == null) {
//            LOGGER.error("Can't delete customer. Invalid Id: [" + id + "]!");
            throw new EntityNotFoundException("Can't delete customer. Invalid Id [" + id + "]!");
        }
        customerDAO.remove(customer);
        return id;
    }
}
