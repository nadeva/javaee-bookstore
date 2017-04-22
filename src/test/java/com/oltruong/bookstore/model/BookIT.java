package com.oltruong.bookstore.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author oltruong
 */
public class BookIT {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void initEntityManager() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @After
    public void closeEntityManager() throws Exception {
        if (entityManager != null) {
            entityManager.close();
        }
    }


    @Test
    public void createBook() throws Exception {

        Book book = new Book();

        book.setName("Passionate Programmer");
        book.setAuthor("Chad Fowler");

        assertNull("ID should be null", book.getId());

        // Persists the book to the database
        transaction.begin();
        entityManager.persist(book);
        transaction.commit();
        assertNotNull("ID should not be null", book.getId());

        // Retrieves all the books from the database
        List<Book> books = entityManager.createNamedQuery(Book.FIND_ALL,Book.class).getResultList();
        assertEquals("1 book should be found", 1, books.size());

        Book bookFromDatabase = books.get(0);

        assertEquals("Books should be the same", book, bookFromDatabase);

    }
}
