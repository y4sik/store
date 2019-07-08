package com.yasik.service;

import com.yasik.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getCustomerFeedback(long customerId);

    List<Feedback> getProductFeedback(long productId);

    Feedback leaveFeedback(Feedback feedback, long productId);

    long deleteFeedback(long id);
}
