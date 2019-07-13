package com.yasik.model.graph.impl;

import com.yasik.model.entity.customer.Customer;
import com.yasik.model.graph.DefaultGraph;
import com.yasik.model.graph.GraphType;
import com.yasik.model.graph.MyGraph;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

public class CustomerGraph extends DefaultGraph implements MyGraph {

    private EntityManager entityManager;

    public CustomerGraph(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityGraph<Customer> getGraph(GraphType graphName) {
        switch (graphName) {
            case CUSTOMER_WITH_AUTHORITIES_ADDRESSES_FEEDBACK_ORDERS:
                return CustomerWithAuthoritiesAddressesFeedbackOrders();
            case CUSTOMER_WITH_AUTHORITIES:
                return CustomerWithAuthorities();
            case CUSTOMER_WITH_ADDRESSES:
                return CustomerWithAddresses();
            case PURE_ENTITY:
                return pureEntity(Customer.class);
        }
        return null;
    }

    private EntityGraph<Customer> CustomerWithAuthoritiesAddressesFeedbackOrders() {
        EntityGraph<Customer> graph = entityManager.createEntityGraph(Customer.class);
        graph.addAttributeNodes("authorities");
        graph.addAttributeNodes("addresses");
        graph.addAttributeNodes("feedback");
        graph.addAttributeNodes("orders");
        return graph;
    }

    private EntityGraph<Customer> CustomerWithAuthorities() {
        EntityGraph<Customer> graph = entityManager.createEntityGraph(Customer.class);
        graph.addAttributeNodes("authorities");
        return graph;
    }

    private EntityGraph<Customer> CustomerWithAddresses() {
        EntityGraph<Customer> graph = entityManager.createEntityGraph(Customer.class);
        graph.addAttributeNodes("addresses");
        return graph;
    }

}
