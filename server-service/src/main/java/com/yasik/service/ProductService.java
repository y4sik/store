package com.yasik.service;

import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(GraphType graphType);

    Product getProduct(long id, GraphType graphType);

    Product addProduct(Product product);

    Product updateProduct(Product product);

    long deleteProduct(long id);
}
