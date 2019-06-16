package com.yasik.web.controller;

import com.yasik.service.AddressService;
import com.yasik.model.entity.customer.Address;
import com.yasik.model.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRestController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/addresses")
    public Address addAddress(@RequestBody Address address) {
        long customerIdFromSession = 8;
        Customer customer = new Customer();
        customer.setId(customerIdFromSession);
        address.setCustomer(customer);
        addressService.saveAddress(address);
        return address;
    }

}
