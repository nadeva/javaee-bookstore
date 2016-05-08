package com.oltruong.bookstore.view;

import com.oltruong.bookstore.model.Book;
import com.oltruong.bookstore.service.BookService;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@Stateful
@ConversationScoped
public class BookBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Book book;

    private Book example = new Book();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getExample() {
        return this.example;
    }

    public void setExample(Book example) {
        this.example = example;
    }


    @Inject
    private Conversation conversation;

    @Inject
    private BookService bookService;


    public String create() {

        this.conversation.begin();
        this.conversation.setTimeout(1800000L);
        return "create?faces-redirect=true";
    }

    public void retrieve() {

        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }

        if (this.conversation.isTransient()) {
            this.conversation.begin();
            this.conversation.setTimeout(1800000L);
        }

        if (this.id == null) {
            this.book = this.example;
        } else {
            this.book = findById(getId());
        }
    }

    public Book findById(Long id) {
        return bookService.find(id);
    }

    public String update() {
        this.conversation.end();
        try {
            if (this.id == null) {
                bookService.save(this.book);
                return "search?faces-redirect=true";
            } else {
                bookService.update(this.book);
                return "view?faces-redirect=true&id=" + this.book.getId();
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public String delete() {
        this.conversation.end();
        try {
            Book deletableEntity = findById(getId());
            bookService.delete(deletableEntity);
            bookService.flush();
            return "search?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
    }

    public String search() {
        return null;
    }

    public List<Book> getAll() {
        return bookService.searchBooks(example);
    }


}