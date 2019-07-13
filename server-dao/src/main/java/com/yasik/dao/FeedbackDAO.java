package com.yasik.dao;


import com.yasik.model.entity.Feedback;

import java.util.List;

public interface FeedbackDAO extends GenericDAO<Feedback> {
    List<Feedback> getFeedbacksByProductId(long productId);

    List<Feedback> getFeedbacksByCustomerId(long customerId);

    List<Feedback> getFeedbacksByCustomerProductId(long customerId, long productId);

    List<Feedback> getFeedbacksByCustomerFeedbackId(long customerId, long feedbackId);
}
