package com.yasik.dao.impl;

import com.yasik.dao.CustomerDAO;
import com.yasik.model.entity.customer.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomerDAOImpl extends GenericDAOImpl<Customer> implements CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDAOImpl() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = query.from(Customer.class);
        customerRoot.fetch("authorities");
        query.select(customerRoot).where(criteriaBuilder.equal(customerRoot.get("username"), username));
        return entityManager.createQuery(query).getResultList();
    }
}
