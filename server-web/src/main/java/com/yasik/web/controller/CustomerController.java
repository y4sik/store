package com.yasik.web.controller;

import com.yasik.service.CustomerService;
import com.yasik.model.graph.GraphType;
import com.yasik.model.entity.customer.Customer;
import com.yasik.web.dto.CustomerDto;
import com.yasik.web.dto.transfer.CustomerMapper;
import com.yasik.web.dto.transfer.View;
import com.yasik.web.exceptionHandling.CustomerNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomerMapper mapper;

    @GetMapping("/customers")
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerService.getCustomers(GraphType.CUSTOMER_WITH_AUTHORITIES);
        List<CustomerDto> customerDto = new ArrayList<>();
        for (Customer customer : customers) {
            customerDto.add(mapper.convertToDto(customer));
        }
        return customerDto;
    }

    //@JsonView({View.UserPrivilege.class})
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable long customerId) {
        Customer customer = customerService.getCustomer(customerId, GraphType.CUSTOMER_WITH_AUTHORITIES);
        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Id: " + customerId);
        }
        LOGGER.info("_________________ " + customer);
        //LOGGER.info("_________________ " + customer.getAddresses());
        //mapper.convertToDto(customer);
        return customer;
    }

    @PostMapping("/customers")
    public Customer registration(@Validated(View.New.class) @RequestBody CustomerDto customerDto) {
        Customer customer = mapper.convertToEntity(customerDto);
        customer.setId(0);
        if (userDetailsService.loadUserByUsername(customer.getUsername()) == null) {
            customerService.saveCustomer(customer);
            LOGGER.info("Customer " + customer.getName() + " successfully registered!");
        }
        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public long deleteAccount(@PathVariable long customerId) {
        Customer customer = customerService.getCustomer(customerId, GraphType.CUSTOMER_WITH_AUTHORITIES);
        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Id: " + customerId);
        }
        customerService.deleteCustomer(customer);
        return customerId;
    }

}
