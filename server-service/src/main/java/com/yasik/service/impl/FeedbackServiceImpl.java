package com.yasik.service.impl;

import com.yasik.dao.FeedbackDAO;
import com.yasik.dao.ProductDAO;
import com.yasik.model.entity.Feedback;
import com.yasik.model.entity.customer.Customer;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;
import com.yasik.service.FeedbackService;
import com.yasik.service.exception.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackServiceImpl.class);


    @Autowired
    private FeedbackDAO feedbackDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public List<Feedback> getCustomerFeedbacks(long customerId) {
        List<Feedback> feedback = feedbackDAO.getFeedbacksByCustomerId(customerId);
        if (feedback.size() == 0) {
            throw new EntityNotFoundException("Customer with id [" + customerId + "] did't leave Feedbacks!");
        }
        return feedback;
    }

    @Override
    @Transactional
    public List<Feedback> getFeedbacksAboutProduct(long productId) {
        List<Feedback> feedbacks = feedbackDAO.getFeedbacksByProductId(productId);
        if (feedbacks.size() == 0) {
            throw new EntityNotFoundException("Product with id [" + productId + "] has no Feedbacks!");
        }
        feedbacks.forEach(feed-> LOGGER.info(feed.getCustomer()));
        return feedbacks;
    }

    @Override
    @Transactional
    public Feedback leaveFeedback(Feedback feedback, long customerId) {
        long productId = feedback.getProduct().getId();
        Product product = productDAO.getById(productId, GraphType.PURE_ENTITY);
        if (product == null) {
            throw new EntityNotFoundException("Can't leave Feedback. " +
                    "No Product with id [" + productId + "]!");
        }
        Customer customer = new Customer();
        customer.setId(customerId);
        feedback.setProduct(product);
        feedback.setCustomer(customer);
        feedback.setDate(LocalDate.now());
        feedback.setTime(LocalTime.now());
        return feedbackDAO.persist(feedback);
    }

    @Override
    @Transactional
    public long deleteCustomerFeedback(long customerId, long feedbackId) {
        List<Feedback> feedbacks = feedbackDAO.getFeedbacksByCustomerFeedbackId(customerId, feedbackId);
        if (feedbacks.size() == 0) {
            throw new EntityNotFoundException("Customer with id [" + customerId + "], " +
                    "has no Feedback with id [" + feedbackId + "]!");
        }
        feedbackDAO.remove(feedbacks.get(0));
        return feedbacks.get(0).getId();
    }

    @Override
    @Transactional
    public long deleteFeedback(long id) {
        Feedback feedback = feedbackDAO.getById(id, GraphType.PURE_ENTITY);
        if (feedback == null) {
            throw new EntityNotFoundException("Can't delete Feedback. Invalid Id [" + id + "]!");
        }
        feedbackDAO.remove(feedback);
        return id;
    }
}
