package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import sun.text.resources.cldr.om.FormatData_om;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerServiceDBImpl implements CustomerService {
    EntityManagerFactory emf;

    public CustomerServiceDBImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(Customer customer) {
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (RollbackException ex){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer;
        EntityManager em = emf.createEntityManager();

        try{
            customer = em.find(Customer.class, id);
        } finally {
            em.close();
        }

        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customer;
        EntityManager em = emf.createEntityManager();

        try{
            Query query = em.createQuery("SELECT * FROM Customer c ");
            customer =  query.getResultList();
        } finally {
            em.close();
        }

        return customer;
    }

    @Override
    public Set<Integer> getCustomerIds() {
        EntityManager em = emf.createEntityManager();
        Set<Integer> set = null;

        try {
            Query query = em.createQuery("SELECT c.id FROM Customer c");
            List<Integer> list = query.getResultList();

            for (Integer i : list) {
                set.add(i);
            }
        } finally {
            em.close();
        }


        return set;
    }

    @Override
    public double getBalance(int customerId) {
        Customer customer;
        EntityManager em = emf.createEntityManager();
        double balance = 0;

        try {
            customer = em.find(Customer.class, customerId);
            List<Account> accounts = customer.getAccounts();

            for (Account account : accounts) {
                balance += account.getBalance();
            }
        } finally {
            em.close();
        }


        return balance;

    }

    @Override
    public Set<Integer> getCustomerAccountNumbers(Integer id) {
        EntityManager em = emf.createEntityManager();
        Set<Integer> set = new HashSet<>();

        try {
            Customer customer = em.find(Customer.class, id);
            List<Account> list = customer.getAccounts();
            set = getAccountIDs(list);
        } finally {
            em.close();
        }

        return set;
    }

    private Set<Integer> getAccountIDs(List<Account> list) {
        Set<Integer> set = new HashSet<>();
        for (Account account : list) {
            set.add(account.getId());
        }
        return set;
    }
}
