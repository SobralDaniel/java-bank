package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    public JpaCustomerDao() {
        super(Customer.class);
    }

    public List<Integer> getCustomerIds() {
        return em.createQuery("select id from Customer", Integer.class)
                .getResultList();

    }
}
