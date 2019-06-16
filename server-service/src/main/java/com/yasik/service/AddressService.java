package com.yasik.service;

import com.yasik.model.entity.customer.Address;
import com.yasik.model.graph.GraphType;

import java.util.List;

public interface AddressService {

    List<Address> getAddresses(GraphType graphType);//default grpaphType

    void saveAddress(Address address);

    void updateAddress(Address address);

    Address getAddress(long id, GraphType graphType);

    void deleteAddress(Address address);
}
