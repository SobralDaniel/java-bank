package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class AuthServiceDBImpl implements AuthService {
    EntityManagerFactory emf;
    private Customer acessingCustomer;

    public AuthServiceDBImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean authenticate(Integer id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.id LIKE :customerID");
        query.setParameter("customerID", id);
        try{
            acessingCustomer = (Customer) query.getSingleResult();
        }catch (NoResultException exception){
            acessingCustomer = null;
        }
        em.close();
        return acessingCustomer != null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return acessingCustomer;
    }
}
