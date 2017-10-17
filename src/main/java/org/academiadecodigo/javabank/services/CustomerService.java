package org.academiadecodigo.javabank.services;


import java.util.Set;

public interface CustomerService {

    double getBalance(Integer id);

    Set<Integer> getCustomerAccountIds(Integer id);

}
