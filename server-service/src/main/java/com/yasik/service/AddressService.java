package com.yasik.service;

import com.sun.javaws.jnl.ExtDownloadDesc;
import com.yasik.model.entity.customer.Address;
import com.yasik.model.graph.GraphType;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses(GraphType graphType);

    Address getAddress(long id, GraphType graphType);

    Address saveAddress(Address address, long customerId);

    Address getCustomerAddress(long customerId, long addressId);

    long deleteAddress(long id);

    List<Address> getCustomerAddresses(long customerId);

    long deleteCustomerAddress(long customerId, long addressId);
}
