package org.academiadecodigo.javabank.services.mock;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.*;

public class MockCustomerService extends AbstractMockService<Customer> implements CustomerService {

    @Override
    public Customer get(Integer id) {
        return modelMap.get(id);
    }

    @Override
    public double getBalance(Integer customerId) {

        List<Account> accounts = modelMap.get(customerId).getAccounts();

        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;

    }

    @Override
    public Set<Integer> getCustomerAccountIds(Integer id) {

        Set<Integer> accountIds = new HashSet<>();
        List<Account> accounts = modelMap.get(id).getAccounts();

        for (Account account : accounts) {
            accountIds.add(account.getId());
        }

        return accountIds;
    }

    @Override
    public List<Customer> list() {
        return new ArrayList<>(modelMap.values());
    }

    @Override
    public Customer save(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        modelMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public void delete(Integer id) {
        modelMap.remove(id);
    }
}
