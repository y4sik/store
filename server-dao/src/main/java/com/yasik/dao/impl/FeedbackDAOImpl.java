package com.yasik.dao.impl;

import com.yasik.dao.FeedbackDAO;
import com.yasik.model.entity.Feedback;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.entity.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FeedbackDAOImpl extends GenericDAOImpl<Feedback> implements FeedbackDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public FeedbackDAOImpl() {
        super(Feedback.class);
    }

    @Override
    public List<Feedback> getFeedbacksByProductId(long productId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Feedback> query = criteriaBuilder.createQuery(Feedback.class);
        Root<Feedback> feedbackRoot = query.from(Feedback.class);
//        feedbackRoot.fetch("customer", JoinType.INNER);
        Join<Feedback, Product> feedbackProductJoin = feedbackRoot.join("product");
        query.select(feedbackRoot).where(criteriaBuilder.equal(
                feedbackProductJoin.get("id"), productId));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Feedback> getFeedbacksByCustomerId(long customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Feedback> query = criteriaBuilder.createQuery(Feedback.class);
        Root<Feedback> feedbackRoot = query.from(Feedback.class);
        Join<Feedback, Customer> feedbackCustomerJoin = feedbackRoot.join("customer");
//        feedbackRoot.fetch("product", JoinType.INNER);
        query.select(feedbackRoot).where(criteriaBuilder.equal(
                feedbackCustomerJoin.get("id"), customerId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Feedback> getFeedbacksByCustomerProductId(long customerId, long productId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Feedback> query = criteriaBuilder.createQuery(Feedback.class);
        Root<Feedback> feedbackRoot = query.from(Feedback.class);
        query.select(feedbackRoot).where(criteriaBuilder.and(
                criteriaBuilder.equal(feedbackRoot.get("customer"), customerId),
                criteriaBuilder.equal(feedbackRoot.get("product"), productId)));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Feedback> getFeedbacksByCustomerFeedbackId(long customerId, long feedbackId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Feedback> query = criteriaBuilder.createQuery(Feedback.class);
        Root<Feedback> feedbackRoot = query.from(Feedback.class);
        query.select(feedbackRoot).where(criteriaBuilder.and(
                criteriaBuilder.equal(feedbackRoot.get("customer"), customerId),
                criteriaBuilder.equal(feedbackRoot.get("id"), feedbackId)));
        return entityManager.createQuery(query).getResultList();
    }


}
