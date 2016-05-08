package com.oltruong.bookstore.view;

import com.google.common.collect.Lists;
import com.oltruong.bookstore.model.Order;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named
@Stateful
@ConversationScoped
public class OrderBean implements Serializable {

    @Inject
    private Conversation conversation;


    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Order order;

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public List<Order> getAll() {
        return Lists.newArrayList();
    }

    public void retrieve() {
        if (this.conversation.isTransient()) {
            this.conversation.begin();
            this.conversation.setTimeout(1800000L);
        }
        if (this.id == null) {
            this.order = new Order();
        } else {
            this.order = findById(getId());
        }
    }

    private Order findById(Long id) {

        return null;
    }


}