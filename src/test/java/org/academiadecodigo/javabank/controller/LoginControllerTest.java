package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class LoginControllerTest {

    private LoginController loginController;
    private View view;

    @Before
    public void setupUp() {
        loginController = new LoginController();
        view = Mockito.mock(View.class);
        loginController.setView(view);
    }

    @Test
    public void initTest() {
        loginController.init();

        Mockito.verify(view).show();
    }

    @Test
    public void onLoginAuthenticTest() {
        AuthService authService = Mockito.mock(AuthServiceImpl.class);
        loginController.setAuthService(authService);
        MainController mainController = Mockito.mock(MainController.class);
        loginController.setNextController(mainController);

        Mockito.when(authService.authenticate(1)).thenReturn(true);

        loginController.onLogin(1);

        Mockito.verify(authService).authenticate(1);
        Mockito.verify(mainController).init();
        verify(view, never()).show();
    }

    @Test
    public void onLoginNotAuthTest() {

        AuthService authService = Mockito.mock(AuthServiceImpl.class);
        loginController.setAuthService(authService);
        MainController mainController = Mockito.mock(MainController.class);
        loginController.setNextController(mainController);

        Mockito.when(authService.authenticate(anyInt())).thenReturn(false);

        loginController.onLogin(1);

        Mockito.verify(mainController, never()).init();
        verify(view).show();
        verify(mainController, never()).init();
    }


}
