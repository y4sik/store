package com.yasik.dao.impl;

import com.yasik.dao.AddressDAO;
import com.yasik.model.entity.customer.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address> implements AddressDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Address> getAddressesByCustomerId(long customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
        Root<Address> addressRoot = query.from(Address.class);
        query.select(addressRoot).where(criteriaBuilder.equal(addressRoot.get("customer_id"), customerId));
        return entityManager.createQuery(query).getResultList();
    }
}
