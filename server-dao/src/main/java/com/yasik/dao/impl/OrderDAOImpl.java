package com.yasik.dao.impl;

import com.yasik.dao.OrderDAO;
import com.yasik.model.entity.Order;

public class OrderDAOImpl extends GenericDAOImpl<Order> implements OrderDAO {
    public OrderDAOImpl(Class<Order> orderClass) {
        super(orderClass);
    }
}
