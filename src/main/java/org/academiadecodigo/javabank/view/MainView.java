package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.MainController;

public class MainView extends AbstractView {

    private MainController mainController;

    @Override
    public void show() {
        showMenu();
    }

    private void showMenu() {

        MenuInputScanner scanner = new MenuInputScanner(UserOptions.getMessages());
        scanner.setError(Messages.VIEW_MAIN_ERROR);
        scanner.setMessage("\n" + Messages.VIEW_MAIN_MESSAGE + mainController.getCustomerName());
        mainController.onMenuSelection(prompt.getUserInput(scanner));
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
