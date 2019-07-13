package com.yasik.dao.impl;

import com.yasik.dao.ProductDAO;
import com.yasik.model.entity.Order;
import com.yasik.model.entity.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductDAOImpl extends GenericDAOImpl<Product> implements ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductDAOImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getProductsByIdList(List<Long> productsId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> feedbackRoot = query.from(Product.class);
        query.select(feedbackRoot).where(feedbackRoot.get("id").in(productsId));
        return entityManager.createQuery(query).getResultList();
    }
}
