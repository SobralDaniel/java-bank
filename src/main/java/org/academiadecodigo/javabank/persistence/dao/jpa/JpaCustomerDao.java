package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.TransactionException;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaCustomerDao extends GenericJpaDao<Customer> implements CustomerDao {

    public JpaCustomerDao() {
        super(Customer.class);
    }

    public List<Integer> getCustomerIds() {

            return em.createQuery("select id from Customer", Integer.class)
                    .getResultList();

    }
}
