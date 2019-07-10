package com.yasik.dao.impl;

import com.yasik.dao.ProductDAO;
import com.yasik.model.entity.product.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl extends GenericDAOImpl<Product> implements ProductDAO {
    public ProductDAOImpl() {
        super(Product.class);
    }
}
