package com.yasik.model.graph.impl;

import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;
import com.yasik.model.graph.MyGraph;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

public class ProductGraph extends DefaultGraph<Product> implements MyGraph<Product> {

    private EntityManager entityManager;

    public ProductGraph(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityGraph<Product> getGraph(GraphType graphName) {

        switch (graphName) {
            case PRODUCT_WITH_PICTURE_CATEGORY_FEEDBACK_ORDERS:
                return ProductWithPictureCategoryFeedbackOrders();
            case PRODUCT_WITH_PICTURE_FEEDBACK:
                return ProductWithPictureFeedBack();
            case PURE_ENTITY:
                return pureEntity(Product.class);
        }
        return null;
    }

    private EntityGraph<Product> ProductWithPictureCategoryFeedbackOrders() {
        EntityGraph<Product> graph = entityManager.createEntityGraph(Product.class);
        graph.addAttributeNodes("picture");
        graph.addAttributeNodes("category");
        graph.addAttributeNodes("feedback");
        graph.addAttributeNodes("orders");
        return graph;
    }

    private EntityGraph<Product> ProductWithPictureFeedBack() {
        EntityGraph<Product> graph = entityManager.createEntityGraph(Product.class);
        graph.addAttributeNodes("picture");
        graph.addAttributeNodes("feedback");
        return graph;
    }
}
