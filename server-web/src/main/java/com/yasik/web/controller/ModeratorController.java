package com.yasik.web.controller;


import com.yasik.model.entity.product.Category;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CategoryService;
import com.yasik.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {

    private static final Logger LOGGER = LogManager.getLogger(ModeratorController.class);


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

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
    public Set<Product> getProductsByCategory(@PathVariable long categoryId) {
        return categoryService.getProductsByCategory(categoryId);
    }

}
