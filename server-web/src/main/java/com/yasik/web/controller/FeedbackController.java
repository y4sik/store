package com.yasik.web.controller;

import com.yasik.model.entity.Feedback;
import com.yasik.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback/{productId}")
    public Feedback leaveFeedback(@RequestBody Feedback feedback, @PathVariable long productId) {
        feedbackService.leaveFeedback(feedback, productId);
        return feedback;
    }

    @GetMapping("/feedback/product/{productId}")
    public List<Feedback> getAllFeedbackAboutProduct(@PathVariable long productId) {
        return feedbackService.getProductFeedback(productId);
    }


}
