package com.yasik.service;

import com.yasik.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getCustomerFeedback(long customerId);

    List<Feedback> getProductFeedback(long productId);

    void leaveFeedback(Feedback feedback);
}
