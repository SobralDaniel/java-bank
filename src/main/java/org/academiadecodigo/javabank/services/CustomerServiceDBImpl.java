package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import sun.text.resources.cldr.om.FormatData_om;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.id LIKE :customerID");
        query.setParameter("customerID", id);
        try{
            customer = (Customer) query.getSingleResult();
        }catch (NoResultException exception){
            customer = null;
        }
        em.close();
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customer;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT * FROM Customer c ");
        try{
            customer =  query.getResultList();
        }catch (NoResultException exception){
            customer = null;
        }
        em.close();
        return customer;
    }

    @Override
    public Set<Integer> getCustomerIds() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c.id FROM Customer c");
        List<Integer> list = query.getResultList();
        Set<Integer> set = null;
        for (Integer i : list) {
            set.add(i);
        }
        em.close();
        return set;
    }

    @Override
    public double getBalance(int customerId) {
        Customer customer;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.id LIKE :customerID");
        query.setParameter("customerID", customerId);
        try{
            customer = (Customer) query.getSingleResult();
        }catch (NoResultException exception){
            customer = null;
        }
        List<Account> accounts = customer.getAccounts();
        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }
        em.close();
        return balance;

    }

    @Override
    public Set<Integer> getCustomerAccountNumbers(Integer id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c.id FROM Account c WHERE c.customer.id LIKE :customerID");
        query.setParameter("customerID", id);
        List<Integer> list = query.getResultList();
        Set<Integer> set = new HashSet<>(list);
        em.close();
        return set;
    }
}
