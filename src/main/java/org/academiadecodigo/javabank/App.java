package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.manager.JpaSessionManager;
import org.academiadecodigo.javabank.manager.JpaTransactionManager;
import org.academiadecodigo.javabank.manager.SessionManager;
import org.academiadecodigo.javabank.manager.TransactionManager;
import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.services.jpa.JpaAccountService;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.mock.MockAccountService;
import org.h2.tools.Server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        try {


            H2WebServer h2WebServer = new H2WebServer();
            h2WebServer.start();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);


            App app = new App();
            app.bootStrap(emf);

            emf.close();
            h2WebServer.stop();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void bootStrap(EntityManagerFactory emf) {

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new JpaAccountService());
        bootstrap.setCustomerService(new JpaCustomerService());

        Controller controller = bootstrap.wireObjects(emf);

        // start application
        controller.init();

    }
}
