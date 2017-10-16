package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class AuthServiceDBImpl implements AuthService {
    EntityManagerFactory emf;
    private Customer acessingCustomer=null;

    public AuthServiceDBImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean authenticate(Integer id) {
        EntityManager em = emf.createEntityManager();

        try{
            acessingCustomer = em.find(Customer.class, id);
        } finally {
            em.close();
        }

        return acessingCustomer != null;
    }

    @Override
    public Customer getAccessingCustomer() {
        return acessingCustomer;
    }
}
