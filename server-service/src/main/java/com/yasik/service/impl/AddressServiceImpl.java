package com.yasik.service.impl;

import com.yasik.dao.AddressDAO;
import com.yasik.model.entity.customer.Address;
import com.yasik.model.graph.GraphType;
import com.yasik.service.AddressService;
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
    public List<Address> getAddresses(GraphType graphType) {
        return addressDAO.getAll(graphType);
    }

    @Override
    @Transactional
    public void saveAddress(Address address) {
        addressDAO.persist(address);
    }

    @Override
    @Transactional
    public void updateAddress(Address address) {
        addressDAO.merge(address);
    }

    @Override
    @Transactional
    public Address getAddress(long id, GraphType graphType) {
        return addressDAO.geById(id, graphType);
    }

    @Override
    @Transactional
    public void deleteAddress(Address address) {
        addressDAO.remove(address);
    }
}
