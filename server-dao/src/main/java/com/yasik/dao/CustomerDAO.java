package com.yasik.dao;

import com.yasik.model.entity.customer.Customer;

import java.util.List;

public interface CustomerDAO extends GenericDAO<Customer> {
    List<Customer> findByUsername(String username);
}
