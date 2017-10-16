package org.academiadecodigo.javabank.manager;

import javax.persistence.EntityManager;

public interface SessionManager {

    void startSession();

    void stopSession();

    EntityManager getCurrentSession();
}
