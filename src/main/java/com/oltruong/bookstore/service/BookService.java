package com.oltruong.bookstore.service;


import com.oltruong.bookstore.model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class BookService implements Serializable {

    @Inject
    private EntityManager entityManager;


    public Book find(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void save(Book book) {
        entityManager.persist(book);
    }

    public List<Book> findAll() {
        TypedQuery<Book> typedQuery = entityManager.createNamedQuery(Book.FIND_ALL, Book.class);
        return typedQuery.getResultList();
    }

    public void delete(Book book) {
        Book bookInDatabase = entityManager.find(Book.class, book.getId());
        if (bookInDatabase != null) {
            entityManager.remove(bookInDatabase);
        }
    }

    public Book update(Book book) {
        return entityManager.merge(book);
    }

    public void flush() {
        entityManager.flush();
    }

    public List<Book> searchBooks(Book book) {


        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        TypedQuery<Book> query = this.entityManager.createQuery(criteria
                .select(root).where(getSearchPredicates(root, book)));
        return query.getResultList();


    }

    private Predicate[] getSearchPredicates(Root<Book> root, Book example) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        List<Predicate> predicatesList = new ArrayList<Predicate>();

        String isbn = example.getIsbn();
        if (isbn != null && !"".equals(isbn)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("isbn")), '%' + isbn.toLowerCase() + '%'));
        }
        String name = example.getName();
        if (name != null && !"".equals(name)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("name")), '%' + name.toLowerCase() + '%'));
        }
        String description = example.getDescription();
        if (description != null && !"".equals(description)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("description")), '%' + description.toLowerCase() + '%'));
        }
        String pictureURL = example.getPictureURL();
        if (pictureURL != null && !"".equals(pictureURL)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("pictureURL")), '%' + pictureURL.toLowerCase() + '%'));
        }
        String author = example.getAuthor();
        if (author != null && !"".equals(author)) {
            predicatesList.add(builder.like(builder.lower(root.<String>get("author")), '%' + author.toLowerCase() + '%'));
        }

        return predicatesList.toArray(new Predicate[predicatesList.size()]);
    }

}
