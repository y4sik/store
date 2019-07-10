package com.yasik.model.graph;

import com.yasik.model.entity.customer.Customer;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.MyGraph;
import com.yasik.model.graph.impl.CustomerGraph;
import com.yasik.model.graph.impl.GenericPureGraph;
import com.yasik.model.graph.impl.ProductGraph;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class GraphFactory<Entity> {

    @PersistenceContext
    private EntityManager entityManager;

    public GraphFactory() {

    }

    public MyGraph createGraph(Class<Entity> entityClass) {
        if (entityClass.getClass().isInstance(Customer.class)) {
            return new CustomerGraph(entityManager);
        } else if (entityClass.getClass().isInstance(Product.class)) {
            return new ProductGraph(entityManager);
        } else return new GenericPureGraph(entityClass);
    }

}
