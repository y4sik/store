package com.yasik.model.graph.impl;

import com.yasik.model.graph.DefaultGraph;
import com.yasik.model.graph.GraphType;
import com.yasik.model.graph.MyGraph;

import javax.persistence.EntityGraph;

public class GenericPureGraph<Entity> extends DefaultGraph implements MyGraph {

    private Class<Entity> entityClass;

    public GenericPureGraph(Class<Entity> entity) {
        this.entityClass=entity;
    }

    @Override
    public EntityGraph<Entity> getGraph(GraphType graphName) {
        switch (graphName) {
            case PURE_ENTITY:
                return pureEntity(entityClass);
        }
        return null;
    }
}
