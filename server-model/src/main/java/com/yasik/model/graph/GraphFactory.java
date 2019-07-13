package com.yasik.model.graph;

import com.yasik.model.entity.customer.Customer;
import com.yasik.model.entity.product.Category;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.impl.CategoryGraph;
import com.yasik.model.graph.impl.CustomerGraph;
import com.yasik.model.graph.impl.GenericPureGraph;
import com.yasik.model.graph.impl.ProductGraph;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Component
public class GraphFactory<Entity> {

    @PersistenceContext
    private EntityManager entityManager;

    public GraphFactory() {

    }

    //entityClass.getClass().isInstance(Product.class)
    public MyGraph createGraph(Class<Entity> entityClass) {
        if (Objects.equals(entityClass, Customer.class)) {
            return new CustomerGraph(entityManager);
        } else if (Objects.equals(entityClass, Product.class)) {
            return new ProductGraph(entityManager);
        } else if (Objects.equals(entityClass, Category.class)) {
            return new CategoryGraph(entityManager);
        } else return new GenericPureGraph(entityClass, entityManager);
    }

}
