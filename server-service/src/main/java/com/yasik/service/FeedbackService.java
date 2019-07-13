package com.yasik.service;

import com.yasik.model.entity.Feedback;
import com.yasik.model.entity.customer.Customer;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getCustomerFeedbacks(long customerId);

    List<Feedback> getFeedbacksAboutProduct(long productId);

    Feedback leaveFeedback(Feedback feedback, long customerId);

    long deleteCustomerFeedback(long customerId, long feedbackId);

    long deleteFeedback(long id);
}
