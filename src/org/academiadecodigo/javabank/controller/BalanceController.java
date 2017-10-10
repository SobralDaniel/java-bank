package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthenticationService;

public class BalanceController extends AbstractController {
    private AuthenticationService authenticationService;

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public Customer getActiveCostumer(){
        return authenticationService.getLoginCustomer();
    }
}
