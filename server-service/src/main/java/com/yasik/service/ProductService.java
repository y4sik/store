package com.yasik.service;

import com.yasik.model.entity.Feedback;
import com.yasik.model.entity.product.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    long deleteProduct(long id);



    List<Product> addFeedback(long customerId);

    List<Product> getProducts(long productId);



}
