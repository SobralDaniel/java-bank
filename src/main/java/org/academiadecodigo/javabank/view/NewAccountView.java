package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.NewAccountController;

public class NewAccountView implements View {

    private NewAccountController newAccountController;

    @Override
    public void show() {
        System.out.println("\n" + Messages.VIEW_NEW_ACCOUNT_MESSAGE + newAccountController.getNewAccountId());
    }

    public void setNewAccountController(NewAccountController newAccountController) {
        this.newAccountController = newAccountController;
    }
}
