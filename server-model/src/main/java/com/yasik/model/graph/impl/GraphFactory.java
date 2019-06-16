package com.yasik.model.graph.impl;

import com.yasik.model.entity.customer.Customer;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.MyGraph;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class GraphFactory {

    @PersistenceContext
    private EntityManager entityManager;

    public GraphFactory() {
    }

    public MyGraph createGraph(Object o) {
        if (o.getClass().isInstance(Customer.class)) {
            return new CustomerGraph(entityManager);
        } else if (o.getClass().isInstance(Product.class)) {
            return new ProductGraph(entityManager);
        } else return new CustomerGraph(entityManager);
    }

}
