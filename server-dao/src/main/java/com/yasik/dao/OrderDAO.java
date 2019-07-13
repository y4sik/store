package com.yasik.dao;


import com.yasik.model.entity.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {

    List<Order> getOrdersByCustomerId(long customerId);

    List<Order> getOrdersByProductId(long productId);

}
