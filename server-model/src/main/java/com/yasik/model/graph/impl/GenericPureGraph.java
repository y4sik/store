package com.yasik.model.graph.impl;

import com.yasik.model.graph.DefaultGraph;
import com.yasik.model.graph.GraphType;
import com.yasik.model.graph.MyGraph;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

public class GenericPureGraph<Entity> extends DefaultGraph implements MyGraph {

    private Class<Entity> entityClass;

    public GenericPureGraph(Class<Entity> entity, EntityManager entityManager) {
        super(entityManager);
        this.entityClass = entity;
    }

    @Override
    public EntityGraph<Entity> getGraph(GraphType graphName) {
        return pureEntity(entityClass);
    }
}
