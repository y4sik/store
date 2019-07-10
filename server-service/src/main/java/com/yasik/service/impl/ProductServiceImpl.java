package com.yasik.service.impl;

import com.yasik.dao.ProductDAO;
import com.yasik.model.entity.product.Category;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;
import com.yasik.service.CategoryService;
import com.yasik.service.ProductService;
import com.yasik.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public List<Product> getProducts(GraphType graphType) {
        List<Product> products = productDAO.getAll(graphType);
        if (products.size() == 0) {
            throw new EntityNotFoundException("There are no Products!");
        }
        return products;
    }

    @Override
    @Transactional
    public Product getProduct(long id, GraphType graphType) {
        Product product = productDAO.getById(id, graphType);
        if (product == null) {
            throw new EntityNotFoundException("Product with id [" + id + "], not found!");
        }
        return product;
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        product.setFeedback(null);
        product.setOrders(null);
        product.setPictures(null);
        Category category = product.getCategory();
        if (!Objects.isNull(category)) {
            long categoryId = category.getId();
            if (!Objects.isNull(categoryId))
                category = categoryService.getCategory(categoryId, GraphType.PURE_ENTITY);
            if (category == null) {
                throw new EntityNotFoundException("Can't add Product. " +
                        "No category with id [" + categoryId + "]!");
            }
            return productDAO.persist(product);
        }
        category = new Category();
        category.setId(1);
        product.setCategory(category);
        return productDAO.persist(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        Product updatableProduct = productDAO.getById(product.getId(), GraphType.PURE_ENTITY);
        if (updatableProduct == null) {
            throw new EntityNotFoundException("Can't update Product. Invalid " +
                    "id [" + product.getId() + "]!");
        }
        if (!updatableProduct.equals(product) ||
                !updatableProduct.getCategory().equals(product.getCategory())) {
            if (product.getCategory() != null) {
                updatableProduct.setCategory(product.getCategory());
            }
            updatableProduct.setCost(product.getCost());
            updatableProduct.setDescription(product.getDescription());
            updatableProduct.setName(product.getName());
            updatableProduct.setQuantity(product.getQuantity());
            return productDAO.merge(updatableProduct);
        }
        return updatableProduct;
    }

    @Override
    @Transactional
    public long deleteProduct(long id) {
        Product product = productDAO.getById(id, GraphType.PURE_ENTITY);
        if (product == null) {
            throw new EntityNotFoundException("Can't delete Product. Invalid Id [" + id + "]!");
        }
        productDAO.remove(product);
        return id;
    }

}
