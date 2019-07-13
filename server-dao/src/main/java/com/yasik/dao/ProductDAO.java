package com.yasik.dao;

import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;

import java.util.List;

public interface ProductDAO extends GenericDAO<Product> {

     List<Product> getProductsByIdList(List<Long> productsId);
}
