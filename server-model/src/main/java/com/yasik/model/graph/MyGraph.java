package com.yasik.model.graph;

import javax.persistence.EntityGraph;

public interface MyGraph<Entity> {

    EntityGraph<Entity> getGraph(GraphType graphName);
}
