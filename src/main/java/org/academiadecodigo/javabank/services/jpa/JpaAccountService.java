package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.manager.TransactionManager;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.jpa.JPAGenericDAO;
import org.academiadecodigo.javabank.persistence.jpa.JpaAccountDAO;
import org.academiadecodigo.javabank.services.AccountService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

public class JpaAccountService implements AccountService {

    private TransactionManager tm;
    private JpaAccountDAO accountDAO;

    public void setTm(TransactionManager tm) {
        this.tm = tm;
    }

    public void setAccountDAO(JpaAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void deposit(Integer id, double amount) {

        try {

            tm.beginWrite();

            Account account = accountDAO.findById(id);

            if (account == null) {
                tm.rollback();
                throw new IllegalArgumentException("invalid account id");
            }

            account.credit(amount);
            accountDAO.saveOrUpdate(account);
            tm.commit();

        } catch (RollbackException ex) {

            tm.rollback();

        }
    }

    @Override
    public void withdraw(Integer id, double amount) {

        try {

            tm.beginWrite();
            Account account = accountDAO.findById(id);

            if (account == null) {
                tm.rollback();
                throw new IllegalArgumentException("invalid account");
            }

            account.debit(amount);
            accountDAO.saveOrUpdate(account);

            tm.commit();

        } catch (RollbackException ex) {

            tm.rollback();

        }
    }

    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        try {

            tm.beginWrite();

            Account srcAccount = accountDAO.findById(srcId);
            Account dstAccount = accountDAO.findById(dstId);

            if (srcAccount == null || dstAccount == null) {
                tm.rollback();
                throw new IllegalArgumentException("invalid account id");
            }

            // make sure transaction can be performed
            if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
                srcAccount.debit(amount);
                dstAccount.credit(amount);
            }

            accountDAO.saveOrUpdate(srcAccount);
            accountDAO.saveOrUpdate(dstAccount);

            tm.commit();

        } catch (RollbackException ex) {

            tm.rollback();
        }
    }

    @Override
    public Account addAccount(Account account) {
        Account newAccount = null;
        try{
            tm.beginWrite();
            newAccount = accountDAO.saveOrUpdate(account);

            if (newAccount == null) {
                tm.rollback();
                throw new IllegalArgumentException("invalid account id");
            }
        }catch (RollbackException ex){
            tm.rollback();
        } finally {
            tm.commit();
            return newAccount;
        }
    }
}
