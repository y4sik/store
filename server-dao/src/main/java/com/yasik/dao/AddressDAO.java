package com.yasik.dao;

import com.yasik.model.entity.customer.Address;

import java.util.List;

public interface AddressDAO extends GenericDAO<Address> {
    List<Address> getAddressesByCustomerId(long customerId);
    List<Address> getAddressByCustomerAndAddressId(long customerId, long addressId);
}
