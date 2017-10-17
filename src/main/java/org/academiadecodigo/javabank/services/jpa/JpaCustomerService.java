package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.manager.TransactionManager;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.jpa.CustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JPAGenericDAO;
import org.academiadecodigo.javabank.persistence.jpa.JpaCustomerDAO;
import org.academiadecodigo.javabank.services.CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaCustomerService implements CustomerService {
    private TransactionManager tm;
    private JpaCustomerDAO customerDAO;

    public void setTm(TransactionManager tm) {
        this.tm = tm;
    }

    public void setCustomerDAO(JpaCustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public double getBalance(Integer id) {

        try {
            tm.beginRead();
            Customer customer = customerDAO.findById(id);

            if (customer == null) {
                throw new IllegalArgumentException("Customer does not exists");
            }

            List<Account> accounts = customer.getAccounts();

            double balance = 0;
            for (Account account : accounts) {
                balance += account.getBalance();
            }

            return balance;

        } finally {
            tm.commit();
        }
    }

    @Override
    public Set<Integer> getCustomerAccountIds(Integer id) {


        try {
            tm.beginRead();
            Set<Integer> accountIds = new HashSet<>();

            Customer customer = customerDAO.findById(id);

            if (customer == null) {
                throw new IllegalArgumentException("Customer does not exists");
            }

            List<Account> accounts = customer.getAccounts();

            for (Account account : accounts) {
                accountIds.add(account.getId());
            }

            return accountIds;

        } finally {
            tm.commit();
        }

    }

}
