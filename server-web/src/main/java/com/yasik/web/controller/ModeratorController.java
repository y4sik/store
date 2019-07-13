package com.yasik.web.controller;


import com.yasik.model.entity.Feedback;
import com.yasik.model.entity.Order;
import com.yasik.model.entity.product.Category;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CategoryService;
import com.yasik.service.FeedbackService;
import com.yasik.service.OrderService;
import com.yasik.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {

    private static final Logger LOGGER = LogManager.getLogger(ModeratorController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts(GraphType.PURE_ENTITY);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable long id) {
        return productService.getProduct(id, GraphType.PURE_ENTITY);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        LOGGER.info("Product [" + newProduct + "], was successfully added!");
        return newProduct;
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        LOGGER.info("Product [" + updatedProduct + "], was successfully updated!");
        return updatedProduct;
    }


    @DeleteMapping("/products/{id}")
    public long deleteProduct(@PathVariable long id) {
        long productId = productService.deleteProduct(id);
        LOGGER.info("Product with id [" + id + "], was successfully deleted!");
        return productId;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories(GraphType.PURE_ENTITY);
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable long id) {
        return categoryService.getCategory(id, GraphType.PURE_ENTITY);
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        Category newCategory = categoryService.addCategory(category);
        LOGGER.info("Category [" + newCategory + "], was successfully added!");
        return newCategory;
    }

    @PutMapping("/categories")
    public Category updateProduct(@RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(category);
        LOGGER.info("Category [" + updatedCategory + "], was successfully updated!");
        return updatedCategory;
    }


    @DeleteMapping("/categories/{id}")
    public long deleteCategory(@PathVariable long id) {
        long categoryId = categoryService.deleteCategory(id);
        LOGGER.info("Category with id [" + id + "], was successfully deleted!");
        return categoryId;
    }

    @GetMapping("/product_by_category/{categoryId}")
    public Category getProductsByCategory(@PathVariable long categoryId) {
        return categoryService.getProductsByCategory(categoryId);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getOrders(GraphType.PURE_ENTITY);
    }

    @GetMapping("/customer_orders/{customerId}")
    public List<Order> getCustomerOrders(@PathVariable long customerId) {
        return orderService.getCustomerOrders(customerId);
    }

    @GetMapping("/product_orders/{productId}")
    public List<Order> getProductOrders(@PathVariable long productId) {
        return orderService.getProductOrders(productId);
    }

    @GetMapping("feedbacks/{customerId}")
    public List<Feedback> getCustomerFeedbacks(@PathVariable long customerId) {
        return feedbackService.getCustomerFeedbacks(customerId);
    }

    @DeleteMapping("/feedbacks/{id}")
    public long deleteFeedback(@PathVariable long id) {
        long deletedId = feedbackService.deleteFeedback(id);
        LOGGER.info("Feedback with id [" + id + "], was successfully deleted!");
        return deletedId;
    }


}
