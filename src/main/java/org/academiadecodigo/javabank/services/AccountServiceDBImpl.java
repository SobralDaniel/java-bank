package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.model.account.CheckingAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class AccountServiceDBImpl implements AccountService {
    EntityManagerFactory emf;

    public AccountServiceDBImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(Account account,Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        account.setCustomer(customer);
        em.persist(account);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deposit(int id, double amount) {
        Account account;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT acc FROM Account acc WHERE acc.id LIKE :accountID");
        query.setParameter("accountID", id);
        try{
            account = (Account)query.getSingleResult();
        }catch (NoResultException exception){
            account = null;
        }
        if(account!=null){
            account.credit(amount);
        }
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void withdraw(int id, double amount) {
        Account account;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT acc FROM Account acc WHERE acc.id LIKE :accountID");
        query.setParameter("accountID", id);
        try{
            account = (Account)query.getSingleResult();
        }catch (NoResultException exception){
            account = null;
        }
        if(account.getAccountType()== AccountType.SAVINGS){
            return;
        }
        account.debit(amount);
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        Account srcAcc, dstAcc;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT acc FROM Account acc WHERE acc.id LIKE :accountID");
        query.setParameter("accountID", srcId);
        try{
            srcAcc = (Account)query.getSingleResult();
        }catch (NoResultException exception){
            srcAcc = null;
        }
        Query query1 = em.createQuery("SELECT acc FROM Account acc WHERE acc.id LIKE :accountID");
        query.setParameter("accountID", dstId);
        try{
            dstAcc = (Account)query.getSingleResult();
        }catch (NoResultException exception){
            dstAcc = null;
        }
        if (srcAcc.canDebit(amount) && dstAcc.canCredit(amount)){
            srcAcc.debit(amount);
            dstAcc.credit(amount);
        }
        em.getTransaction().begin();
        em.persist(srcAcc);
        em.persist(dstAcc);
        em.getTransaction().commit();
        em.close();
    }
}
