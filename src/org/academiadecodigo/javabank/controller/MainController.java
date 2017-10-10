package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.view.Messages;
import org.academiadecodigo.javabank.view.UserOptions;

import java.util.Map;

public class MainController extends AbstractController {

    private Map<Integer, Controller> controllerMap;
    private AuthenticationService authenticationService;

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setControllerMap(Map<Integer, Controller> controllerMap) {
        this.controllerMap = controllerMap;
    }

    public void onMenuSelection(int option) {

        if (option == UserOptions.QUIT.getOption()) {
            return;
        }

        if (!controllerMap.containsKey(option)) {
            throw new IllegalStateException(Messages.SYSTEM_ERROR);
        }

        controllerMap.get(option).init();
        init();

    }

    public String getLoginCustomerName(){
        return authenticationService.getLoginCustomer().getName();
    }

}
