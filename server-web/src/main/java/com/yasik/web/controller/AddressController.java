package com.yasik.web.controller;

import com.yasik.model.entity.customer.Address;
import com.yasik.model.entity.customer.Customer;
import com.yasik.service.AddressService;
import com.yasik.web.annotation.CurrentCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    private static final Logger LOGGER = LogManager.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public List<Address> getCustomerAddresses(@CurrentCustomer Customer customer) {
        return addressService.getCustomerAddresses(customer.getId());
    }

    @DeleteMapping("/addresses/{addressId}")
    public long deleteCustomerAddress(@PathVariable long addressId, @CurrentCustomer Customer customer) {
        long id = addressService.deleteCustomerAddress(customer.getId(), addressId);
        LOGGER.info("Address with id[" + addressId + "], was successfully deleted!");
        return id;
    }

    @PostMapping("/addresses")
    public Address addCustomerAddress(@RequestBody Address address, @CurrentCustomer Customer currentCustomer) {
        Address addedAddress = addressService.saveAddress(address, currentCustomer.getId());
        LOGGER.info("Address [" + address + "], was successfully added, " +
                "for customer with id[" + currentCustomer.getId() + "]!");
        return addedAddress;
    }

    @GetMapping("/addresses/{addressId}")
    public Address getCustomerAddress(@CurrentCustomer Customer currentCustomer, @PathVariable long addressId) {
        return addressService.getCustomerAddress(currentCustomer.getId(), addressId);
    }

}
