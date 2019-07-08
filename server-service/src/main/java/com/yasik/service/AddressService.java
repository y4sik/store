package com.yasik.service;

import com.yasik.model.entity.customer.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();

    Address saveAddress(Address address, long customerId);

    Address getCustomerAddress(long customerId, long addressId);

    long deleteAddress(long id);

    List<Address> getCustomerAddresses(long customerId);

    long deleteCustomerAddress(long customerId, long addressId);
}
