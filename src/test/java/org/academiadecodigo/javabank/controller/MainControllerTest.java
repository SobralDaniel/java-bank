package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.view.UserOptions;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class MainControllerTest {
    private MainController mainController;
    private View view;
    private Map<Integer, Controller> map;

    @Before
    public void setup(){
        mainController = new MainController();
        view = Mockito.mock(View.class);
        mainController.setView(view);
        map = Mockito.mock(Map.class);
        mainController.setControllerMap(map);
    }

    @Test
    public void initTest(){
        mainController.init();

        verify(view).show();
    }

    @Test
    public void onMenuSelectionQuitTest(){
        mainController.onMenuSelection(UserOptions.QUIT.getOption());


    }

    @Test
    public void onMenuSelectionExistTest(){

    }

}
