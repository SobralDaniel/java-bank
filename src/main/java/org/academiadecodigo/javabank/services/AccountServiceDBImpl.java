package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import javax.persistence.*;

public class AccountServiceDBImpl implements AccountService {
    EntityManagerFactory emf;

    public AccountServiceDBImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void add(Account account, Customer customer) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            account.setCustomer(customer);
            em.persist(account);
            em.getTransaction().commit();
        } catch (RollbackException ex){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void deposit(int id, double amount) {

        Account account;
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            account = em.find(Account.class, id);

            //if (account != null) {
            account.credit(amount);
            //}
            em.persist(account);

            em.getTransaction().commit();
        } catch (RollbackException ex){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }


    }

    @Override
    public void withdraw(int id, double amount) {
        Account account;
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            account = em.find(Account.class, id);

            if (account.getAccountType()== AccountType.SAVINGS){
                em.close();
                return;
            }

            account.debit(amount);

            em.persist(account);
            em.getTransaction().commit();
        } catch (RollbackException ex){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        Account srcAcc, dstAcc;
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            srcAcc = em.find(Account.class, srcId);
            dstAcc = em.find(Account.class, dstId);
            if (srcAcc.canDebit(amount) && dstAcc.canCredit(amount)) {
                srcAcc.debit(amount);
                dstAcc.credit(amount);
            }
            em.persist(srcAcc);
            em.persist(dstAcc);
            em.getTransaction().commit();
        } catch (RollbackException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
