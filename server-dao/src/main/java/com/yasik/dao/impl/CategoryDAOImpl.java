package com.yasik.dao.impl;

import com.yasik.dao.CategoryDAO;
import com.yasik.model.entity.product.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl extends GenericDAOImpl<Category> implements CategoryDAO {
    public CategoryDAOImpl() {
        super(Category.class);
    }
}
