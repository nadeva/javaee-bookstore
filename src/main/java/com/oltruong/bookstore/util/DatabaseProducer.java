package com.oltruong.bookstore.util;


import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


public class DatabaseProducer {

    @Produces
    @PersistenceContext(unitName = "javaee-application-persistence-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
}
