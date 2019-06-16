package com.yasik.dao.impl;

import com.yasik.dao.FeedbackDAO;
import com.yasik.model.entity.Feedback;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FeedbackDAOImpl extends GenericDAOImpl<Feedback> implements FeedbackDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Feedback> getFeedbackByProductId(long productId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Feedback> query = builder.createQuery(Feedback.class);
        Root<Feedback> feedbackRoot = query.from(Feedback.class);
        feedbackRoot.fetch("customer", JoinType.INNER);

        query.select(feedbackRoot).where(builder.equal(feedbackRoot.get("product"), productId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Feedback> getFeedbackByCustomerId(long customerId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Feedback> query = builder.createQuery(Feedback.class);
        Root<Feedback> feedbackRoot = query.from(Feedback.class);
        feedbackRoot.fetch("product", JoinType.INNER);
        query.select(feedbackRoot).where(builder.equal(feedbackRoot.get("customer"), customerId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Feedback> getFeedbackByCustomerProductId(long customerId, long productId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Feedback> query = builder.createQuery(Feedback.class);
        Root<Feedback> feedbackRoot = query.from(Feedback.class);
        query.select(feedbackRoot).where(builder.and(builder.equal(feedbackRoot.get("customer_id"),
                customerId), builder.equal(feedbackRoot.get("product_id"), productId)));
        return entityManager.createQuery(query).getResultList();
    }
}
