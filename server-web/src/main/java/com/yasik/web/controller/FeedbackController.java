package com.yasik.web.controller;

import com.yasik.service.FeedbackService;
import com.yasik.model.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public Feedback leaveFeedback(@RequestBody Feedback feedback) {
        feedbackService.leaveFeedback(feedback);
        return feedback;
    }

    @GetMapping("/feedback/customer/{customerId}")
    public List<Feedback> getAllCustomerFeedback(@PathVariable long customerId) {
        return feedbackService.getCustomerFeedback(customerId);

    }

    @GetMapping("/feedback/product/{productId}")
    public List<Feedback> getAllFeedbackAboutProduct(@PathVariable long productId) {
        return feedbackService.getProductFeedback(productId);
    }


}
