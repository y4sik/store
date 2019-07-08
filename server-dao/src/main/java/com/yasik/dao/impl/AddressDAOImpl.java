package com.yasik.dao.impl;

import com.yasik.dao.AddressDAO;
import com.yasik.model.entity.customer.Address;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
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

    public AddressDAOImpl() {
        super(Address.class);
    }

    @Override
    public List<Address> getAddressesByCustomerId(long customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
        Root<Address> addressRoot = query.from(Address.class);
        query.select(addressRoot).where(criteriaBuilder.equal(addressRoot.get("customer"), customerId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Address> getAddressByCustomerAndAddressId(long customerId, long addressId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);
        Root<Address> addressRoot=query.from(Address.class);
        query.select(addressRoot).where(criteriaBuilder.equal(addressRoot.get("customer"), customerId),
                 criteriaBuilder.equal(addressRoot.get("id"), addressId));
       return entityManager.createQuery(query).getResultList();
    }
}
