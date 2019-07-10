package com.yasik.dao.impl;

import com.yasik.dao.OrderDAO;
import com.yasik.model.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends GenericDAOImpl<Order> implements OrderDAO {
    public OrderDAOImpl() {
        super(Order.class);
    }
}
