package com.yasik.web.controller;

import com.yasik.model.entity.Feedback;
import com.yasik.model.entity.customer.Address;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;
import com.yasik.service.AddressService;
import com.yasik.service.CustomerService;
import com.yasik.service.FeedbackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private FeedbackService feedbackService;

    @DeleteMapping("/customers/{id}")
    public long deleteAccount(@PathVariable long id) {
        customerService.deleteCustomer(id);
        LOGGER.info("Customer with id [" + id + "], was successfully deleted");
        return id;
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable long customerId) {
        return customerService.getCustomer(customerId, GraphType.PURE_ENTITY);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers(GraphType.CUSTOMER_WITH_AUTHORITIES);
    }

    @GetMapping("/addresses")
    public List<Address> getAddresses() {
        return addressService.getAllAddresses(GraphType.PURE_ENTITY);
    }

    @GetMapping("addresses/{id}")
    public Address getAddress(@PathVariable long id) {
        return addressService.getAddress(id, GraphType.PURE_ENTITY);
    }

    @DeleteMapping("/addresses/{id}")
    public long deleteAddress(@PathVariable long id) {
        long addressId = addressService.deleteAddress(id);
        LOGGER.info("Address with id [" + id + "], was successfully deleted");
        return addressId;
    }

    @GetMapping("/addresses/{customerId}")
    public List<Address> getCustomerAddresses(@PathVariable long customerId) {
        return addressService.getCustomerAddresses(customerId);
    }

    @GetMapping("/feedback/customer/{customerId}")
    public List<Feedback> getAllCustomerFeedback(@PathVariable long customerId) {
        return feedbackService.getCustomerFeedback(customerId);
    }

}

