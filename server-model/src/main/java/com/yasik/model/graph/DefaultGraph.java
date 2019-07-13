package com.yasik.model.graph;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

public abstract class DefaultGraph<Entity> {


    private EntityManager entityManager;

    public DefaultGraph() {
    }

    public DefaultGraph(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityGraph<Entity> pureEntity(Class<Entity> entityClass) {
        EntityGraph<Entity> graph = entityManager.createEntityGraph(entityClass);
        return graph;
    }
}
