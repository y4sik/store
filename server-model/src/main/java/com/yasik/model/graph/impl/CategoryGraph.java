package com.yasik.model.graph.impl;

import com.yasik.model.entity.product.Category;
import com.yasik.model.graph.DefaultGraph;
import com.yasik.model.graph.GraphType;
import com.yasik.model.graph.MyGraph;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

public class CategoryGraph extends DefaultGraph implements MyGraph {

    private EntityManager entityManager;

    public CategoryGraph(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityGraph<Category> getGraph(GraphType graphName) {
        switch (graphName) {
            case CATEGORY_WITH_PRODUCTS:
                return CategoryWithProducts();
            case PURE_ENTITY:
                return pureEntity(Category.class);
        }
        return null;
    }

    private EntityGraph<Category> CategoryWithProducts() {
        EntityGraph<Category> graph = entityManager.createEntityGraph(Category.class);
        graph.addAttributeNodes("products");
        return graph;
    }
}
