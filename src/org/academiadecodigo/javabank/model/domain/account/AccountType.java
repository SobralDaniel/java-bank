package org.academiadecodigo.javabank.model.domain.account;

import org.academiadecodigo.javabank.utils.Messages;

public enum AccountType {

    CHECKING(1, Messages.MENU_CHECKING),
    SAVINGS(2, Messages.MENU_SAVINGS);

    private int option;
    private String message;

    public int getOption() {
        return option;
    }

    public String getMessage() {
        return message;
    }

    AccountType(int option, String message) {
        this.option = option;
        this.message = message;
    }

    public static String[] getMessages() {

        String[] messages = new String[values().length];

        for (AccountType option: values()) {
            messages[option.getOption() - 1] = option.getMessage();
        }

        return messages;
    }
}
