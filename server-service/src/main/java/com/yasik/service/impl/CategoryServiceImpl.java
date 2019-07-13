package com.yasik.service.impl;

import com.yasik.dao.CategoryDAO;
import com.yasik.model.entity.product.Category;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CategoryService;
import com.yasik.service.exception.EmptyObjectException;
import com.yasik.service.exception.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);


    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional
    public List<Category> getCategories(GraphType graphType) {
        List<Category> categories = categoryDAO.getAll(graphType);
        if (categories.size() == 0) {
            throw new EntityNotFoundException("There are no Categories!");
        }
        return categories;
    }

    @Override
    @Transactional
    public Category getCategory(long id, GraphType graphType) {
        Category category = categoryDAO.getById(id, graphType);
        if (category == null) {
            throw new EntityNotFoundException("Category with id [" + id + "], not found!");
        }
        return category;
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
        category.setProducts(null);
        return categoryDAO.persist(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        Category updatableCategory = categoryDAO.getById(category.getId(), GraphType.PURE_ENTITY);
        if (updatableCategory == null) {
            throw new EntityNotFoundException("Can't update Product. Invalid " +
                    "id [" + category.getId() + "]!");
        }
        updatableCategory.setName(category.getName());
        if (category.getProducts() != null) {
            updatableCategory.setProducts(category.getProducts());
        }
        return categoryDAO.merge(updatableCategory);
    }

    @Override
    @Transactional
    public long deleteCategory(long id) {
        Category category = categoryDAO.getById(id, GraphType.PURE_ENTITY);
        if (category == null) {
            throw new EntityNotFoundException("Can't delete Category. Invalid Id [" + id + "]!");
        }
        categoryDAO.remove(category);
        return id;
    }

    @Override
    @Transactional
    public Category getProductsByCategory(long categoryId) {
        Category category = getCategory(categoryId, GraphType.CATEGORY_WITH_PRODUCTS);
        if (category.getProducts() == null) {
            throw new EmptyObjectException("Category with id [" + categoryId + "] has no Products!");
        }
        LOGGER.info(category.getProducts());
        return category;
    }


}
