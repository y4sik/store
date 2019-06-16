package com.yasik.dao;


import com.yasik.model.entity.Feedback;

import java.util.List;

public interface FeedbackDAO extends GenericDAO<Feedback> {
    List<Feedback> getFeedbackByProductId(long productId);

    List<Feedback> getFeedbackByCustomerId(long customerId);

    List<Feedback> getFeedbackByCustomerProductId(long customerId, long productId);
}
