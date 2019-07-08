package com.yasik.web.controller;

import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CustomerService;
import com.yasik.web.annotation.CurrentCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserDetailsService userDetailsService;


    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable long customerId, Authentication authentication) {
        Customer currentCustomer = (Customer) authentication.getPrincipal();
        LOGGER.info(currentCustomer);
        Customer customer = customerService.getCustomer(customerId, GraphType.PURE_ENTITY);
        return customer;
    }

    @PostMapping("/customers")
    public Customer registration(@RequestBody Customer customer) {
        customer.setId(0);
        customerService.createCustomer(customer);
        LOGGER.info("Customer [" + customer + "], was successfully registered!");
        return customer;
    }

    @PutMapping("/customers")
    public Customer update(@RequestBody Customer customer, @CurrentCustomer Customer currentCustomer) {
        Customer updatedCustomer = customerService.updateCustomer(customer, currentCustomer);
        LOGGER.info("Customer [" + updatedCustomer + "], was successfully update!");
        return updatedCustomer;
    }


}
