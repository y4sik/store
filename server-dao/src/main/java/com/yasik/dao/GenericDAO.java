package com.yasik.dao;

import com.yasik.model.graph.GraphType;

import java.util.List;

public interface GenericDAO<Entity> {

    Entity persist(Entity entity);

    Entity geById(long id, GraphType graphType);

    List<Entity> getAll(GraphType graphType);

    Entity merge(Entity entity);

    void remove(Entity entity);
}
