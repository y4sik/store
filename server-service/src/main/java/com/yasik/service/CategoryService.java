package com.yasik.service;

import com.yasik.model.entity.product.Category;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<Category> getCategories(GraphType graphType);

    Category getCategory(long id, GraphType graphType);

    Category addCategory(Category category);

    Category updateCategory(Category category);

    long deleteCategory(long id);

    Set<Product> getProductsByCategory(long categoryId);
}
