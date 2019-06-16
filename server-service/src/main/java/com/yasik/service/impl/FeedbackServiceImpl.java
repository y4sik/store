package com.yasik.service.impl;

import com.yasik.dao.FeedbackDAO;
import com.yasik.model.entity.Feedback;
import com.yasik.service.FeedbackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackServiceImpl.class);


    @Autowired
    private FeedbackDAO feedbackDAO;

    @Transactional
    @Override
    public List<Feedback> getCustomerFeedback(long customerId) {
        List<Feedback> feedback = feedbackDAO.getFeedbackByCustomerId(customerId);
        if (feedback.size() == 0) {
            return null;
        }
        return feedback;
    }

    @Transactional
    @Override
    public List<Feedback> getProductFeedback(long productId) {
        List<Feedback> feedbackList = feedbackDAO.getFeedbackByProductId(productId);
        if (feedbackList.size() == 0) {
            return null;
        }

        LOGGER.info("Feedback 0: " + feedbackList.get(0));
        return feedbackList;
    }

    @Transactional
    @Override
    public void leaveFeedback(Feedback feedback) {
        feedbackDAO.persist(feedback);
    }

}
