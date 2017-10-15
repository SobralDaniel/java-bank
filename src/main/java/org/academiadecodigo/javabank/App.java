package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.services.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        try {
            H2WebServer h2WebServer = new H2WebServer();
            h2WebServer.start();


            EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Customer c = new Customer();
            c.setName("Rui");
            em.persist(c);
            em.getTransaction().commit();
            em.close();

            App app = new App();
            app.bootStrap(emf);

            emf.close();
            h2WebServer.stop();

        }
        catch (SQLException ex){
        ex.printStackTrace();
        }

    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setCustomerService(new CustomerServiceImpl());
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new AccountServiceImpl());

        bootstrap.loadCustomers();

        LoginController loginController = bootstrap.wireObjects();

        // start application
        loginController.init();

    }

    private void bootStrap(EntityManagerFactory emf) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setCustomerService(new CustomerServiceDBImpl(emf));
        bootstrap.setAuthService(new AuthServiceDBImpl(emf));
        bootstrap.setAccountService(new AccountServiceDBImpl(emf));

        bootstrap.loadCustomers();

        LoginController loginController = bootstrap.wireObjects();

        // start application
        loginController.init();

    }
}
