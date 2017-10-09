package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.MenuController;
import org.academiadecodigo.javabank.controller.operations.Operation;
import org.academiadecodigo.javabank.utils.Messages;

import java.util.Map;

public class MenuView implements View{

    private Prompt prompt;
    private MenuController menuController;

    public MenuView(Prompt prompt) {
        this.prompt = prompt;
    }

    @Override
    public void show() {
        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);
        menuController.finish(prompt.getUserInput(mainMenu));
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
