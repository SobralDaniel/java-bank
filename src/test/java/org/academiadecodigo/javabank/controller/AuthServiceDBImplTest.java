package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthServiceDBImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AuthServiceDBImplTest {
    EntityManagerFactory emf;
    EntityManager em;
    AuthServiceDBImpl authServiceDB;

    @Before
    public void setup(){
        emf = Mockito.mock(EntityManagerFactory.class);
        em = Mockito.mock(EntityManager.class);
        authServiceDB = new AuthServiceDBImpl(emf);
    }

    @Test
    public void authenticateTest(){
        int id = 1;
        Customer customer = null;
        Mockito.when(emf.createEntityManager()).thenReturn(em);
        Mockito.when(em.find(Customer.class, id)).thenReturn(customer);

        authServiceDB.authenticate(id);

        Mockito.verify(emf).createEntityManager();
        Mockito.verify(em).find(Customer.class, id);
        Assert.assertEquals(customer,em.find(Customer.class,id));
    }
}
