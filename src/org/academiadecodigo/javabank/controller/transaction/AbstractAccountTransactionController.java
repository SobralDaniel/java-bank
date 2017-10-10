
package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.AbstractController;
import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.Set;

public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {
    protected CustomerService customerService;
    private AuthenticationService authenticationService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Set<Integer> getActiveAccountIds() {
        return authenticationService.getLoginCustomer().getAccountIds();
    }
}
