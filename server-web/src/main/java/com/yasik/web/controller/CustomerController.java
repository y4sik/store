package com.yasik.web.controller;

import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CustomerService;
import com.yasik.service.excetpion.handle.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers(GraphType.CUSTOMER_WITH_AUTHORITIES);
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable long customerId) {
        Customer customer = customerService.getCustomer(customerId, GraphType.PURE_ENTITY);
        return customer;
    }

    @PostMapping("/customers")
    public Customer registration(@RequestBody Customer customer) {
        customer.setId(0);
        if (userDetailsService.loadUserByUsername(customer.getUsername()) == null) {
            customerService.createCustomer(customer);
            LOGGER.info("Customer " + customer.getName() + " successfully registered!");
        }
        return customer;
    }

    public Customer logIn(@RequestParam String email, @RequestParam String password){
        return null;
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer){
        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public long deleteAccount(@PathVariable long customerId) {
        Customer customer = customerService.getCustomer(customerId, GraphType.CUSTOMER_WITH_AUTHORITIES);
        if (customer == null) {
            throw new EntityNotFoundException("Invalid Id: " + customerId);
        }
        customerService.deleteCustomer(customer);
        return customerId;
    }

}
