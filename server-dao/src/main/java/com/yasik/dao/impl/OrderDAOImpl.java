package com.yasik.dao.impl;

import com.yasik.dao.OrderDAO;
import com.yasik.model.entity.Order;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.entity.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class OrderDAOImpl extends GenericDAOImpl<Order> implements OrderDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public OrderDAOImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> getOrdersByCustomerId(long customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = query.from(Order.class);
        Join<Order, Customer> orderCustomerJoin = orderRoot.join("customer");
        query.select(orderRoot).where(criteriaBuilder.equal(
                orderCustomerJoin.get("id"), customerId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Order> getOrdersByProductId(long productId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = query.from(Order.class);
        Join<Order, Product> orderProductJoin = orderRoot.join("products");
        query.select(orderRoot).where(criteriaBuilder.equal(
                orderProductJoin.get("id"), productId));
        return entityManager.createQuery(query).getResultList();
    }
}
