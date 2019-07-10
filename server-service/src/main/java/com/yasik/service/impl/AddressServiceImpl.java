package com.yasik.service.impl;

import com.yasik.dao.AddressDAO;
import com.yasik.model.entity.customer.Address;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.GraphType;
import com.yasik.service.AddressService;
import com.yasik.service.exception.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressDAO addressDAO;

    @Override
    @Transactional
    public List<Address> getAllAddresses(GraphType graphType) {
        List<Address> addresses = addressDAO.getAll(graphType);
        if ((addresses.size() == 0)) {
            throw new EntityNotFoundException("There are no addresses!");
        }
        return addresses;
    }

    @Override
    @Transactional
    public Address getAddress(long id, GraphType graphType) {
        Address address = addressDAO.getById(id, graphType);
        if (address == null) {
            throw new EntityNotFoundException("Address with id [" + id + "], not found!");
        }
        return address;
    }

    @Override
    @Transactional
    public Address saveAddress(Address address, long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        address.setCustomer(customer);
        return addressDAO.persist(address);
    }


    @Override
    @Transactional
    public long deleteAddress(long id) {
        Address address = addressDAO.getById(id, GraphType.PURE_ENTITY);
        if (address == null) {
            throw new EntityNotFoundException("Can't delete address. Invalid Id [" + id + "]!");
        }
        addressDAO.remove(address);
        return id;
    }

    @Override
    @Transactional
    public List<Address> getCustomerAddresses(long customerId) {
        List<Address> addresses = addressDAO.getAddressesByCustomerId(customerId);
        if (addresses.size() == 0) {
            throw new EntityNotFoundException("Customer with id [" + customerId + "], has no addresses!");
        }
        return addresses;
    }

    @Override
    @Transactional
    public Address getCustomerAddress(long customerId, long addressId) {
        List<Address> addresses = addressDAO.getAddressByCustomerAndAddressId(customerId, addressId);
        if (addresses.size() == 0) {
            throw new EntityNotFoundException("Customer with id [" + customerId + "], " +
                    "has no address [" + addressId + "]");
        }
        return addresses.get(0);
    }

    @Override
    @Transactional
    public long deleteCustomerAddress(long customerId, long addressId) {
        List<Address> addresses = addressDAO.getAddressByCustomerAndAddressId(customerId, addressId);
        if (addresses.size() == 0) {
            throw new EntityNotFoundException("Customer has no such address!");
        }
        addressDAO.remove(addresses.get(0));
        return addressId;
    }


}
