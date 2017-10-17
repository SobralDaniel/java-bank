package org.academiadecodigo.javabank.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class JpaSessionManager implements SessionManager{

    @PersistenceUnit
    private EntityManagerFactory emf; // the persistence unit
    private EntityManager em; // the persistence context

    public void startSession() {

        if (em == null) {
            em = emf.createEntityManager();
        }
    }

    public void stopSession() {

        if (em != null) {
            em.close();
        }

        em = null;
    }

    public EntityManager getCurrentSession() {
        startSession();
        return em;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
