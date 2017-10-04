package org.academiadecodigo.javabank.test;

public class Test {

    public static void main(String[] args) {

       // AccountTest accountTest = new AccountTest();
        CustomerTest customerTest = new CustomerTest();
        BankTest bankTest = new BankTest();
        CheckingAccountTest checkingaccountTest = new CheckingAccountTest();
        SavingAccountTest savingaccountTest = new SavingAccountTest();

        //System.out.println("Account: " + (accountTest.test() ? "OK" : "FAIL"));
        System.out.println("Customer: " + (customerTest.test() ? "OK" : "FAIL"));
        System.out.println("Bank: " + (bankTest.test() ? "OK" : "FAIL"));
        System.out.println("CheckingAccount: " + (checkingaccountTest.test() ? "OK" : "FAIL"));
        System.out.println("SavingAccount: " + (savingaccountTest.test() ? "OK" : "FAIL"));

    }


}
