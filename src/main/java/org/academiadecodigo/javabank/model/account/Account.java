package org.academiadecodigo.javabank.model.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
public abstract class Account extends AbstractModel {

    private double balance = 0;

    @ManyToOne
    @JsonBackReference
    private Customer customer;

    public void credit(double amount) {
        if (canCredit(amount)) {
            balance += amount;
        }
    }

    public void debit(double amount) {
        if (canDebit(amount)) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public abstract AccountType getAccountType();

    public boolean canDebit(double amount) {
        return amount > 0 && amount <= balance;
    }

    public boolean canCredit(double amount) {
        return amount > 0;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return this.getId().equals(account.getId()) && account.customer.equals(this.customer);

    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", customerId=" + (customer != null ? customer.getId() : null) +
                "} " + super.toString();
    }
}
