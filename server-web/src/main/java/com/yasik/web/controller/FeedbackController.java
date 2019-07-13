package com.yasik.web.controller;

import com.yasik.model.entity.Feedback;
import com.yasik.model.entity.customer.Customer;
import com.yasik.service.FeedbackService;
import com.yasik.web.annotation.CurrentCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    private static final Logger LOGGER = LogManager.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedbacks")
    public List<Feedback> getCustomerFeedbacks(@CurrentCustomer Customer customer) {
        return feedbackService.getCustomerFeedbacks(customer.getId());
    }

    @PostMapping("/feedbacks")
    public Feedback leaveFeedback(@RequestBody Feedback feedback, @CurrentCustomer Customer customer) {
        Feedback newFeedback = feedbackService.leaveFeedback(feedback, customer.getId());
        LOGGER.info("Feedback [" + newFeedback + "], was successfully left!");
        return newFeedback;
    }

    @GetMapping("/feedbacks/{productId}")
    public List<Feedback> getFeedbacksAboutProduct(@PathVariable long productId) {
        return feedbackService.getFeedbacksAboutProduct(productId);
    }

    @DeleteMapping("/feedbacks/{feedbackId}")
    public long deleteFeedback(@PathVariable long feedbackId, @CurrentCustomer Customer customer) {
        long id = feedbackService.deleteCustomerFeedback(customer.getId(), feedbackId);
        LOGGER.info("Feedback with id [" + feedbackId + "], was successfully deleted!");
        return id;
    }

}
