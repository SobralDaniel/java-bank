package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.NewAccountController;
import org.academiadecodigo.javabank.model.account.AccountType;

public class NewAccountView extends AbstractView {

    private NewAccountController newAccountController;

    @Override
    public void show() {
        MenuInputScanner scanner = new MenuInputScanner(AccountType.getMessages());
        scanner.setError(Messages.VIEW_MAIN_ERROR);
        //scanner.setMessage("\n" + Messages.VIEW_NEW_ACCOUNT_MESSAGE + newAccountController.getNewAccountId());
        newAccountController.onAccountTypeSelection(prompt.getUserInput(scanner));
    }

    public void setNewAccountController(NewAccountController newAccountController) {
        this.newAccountController = newAccountController;
    }

    public void showAccountID(){
        System.out.println("\n" + Messages.VIEW_NEW_ACCOUNT_MESSAGE +newAccountController.getNewAccountId());

    }
}
