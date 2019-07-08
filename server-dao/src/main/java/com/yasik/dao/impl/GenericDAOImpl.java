package com.yasik.dao.impl;

import com.yasik.dao.GenericDAO;
import com.yasik.model.graph.GraphType;
import com.yasik.model.graph.impl.GraphFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenericDAOImpl<Entity> implements GenericDAO<Entity> {

    private static final Logger LOGGER = LogManager.getLogger(GenericDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GraphFactory graphFactory;

    private Class<Entity> entityClass;

    public GenericDAOImpl() {
    }

    public GenericDAOImpl(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Entity persist(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Entity geById(long id, GraphType graphType) {
        EntityGraph graph = graphFactory.createGraph(entityClass).getGraph(graphType);
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", graph);
        return entityManager.find(entityClass, id, hints);
    }

    @Override
    public List<Entity> getAll(GraphType graphType) {
        EntityGraph graph = graphFactory.createGraph(entityClass).getGraph(graphType);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entity> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<Entity> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).setHint("javax.persistence.fetchgraph", graph).getResultList();

    }

    @Override
    public Entity merge(Entity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void remove(Entity entity) {
        entityManager.remove(entity);
    }
}
