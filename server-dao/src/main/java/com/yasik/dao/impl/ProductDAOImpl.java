package com.yasik.dao.impl;

import com.yasik.dao.ProductDAO;
import com.yasik.model.entity.product.Product;

public class ProductDAOImpl extends GenericDAOImpl<Product> implements ProductDAO {
    public ProductDAOImpl(Class<Product> productClass) {
        super(productClass);
    }
}
