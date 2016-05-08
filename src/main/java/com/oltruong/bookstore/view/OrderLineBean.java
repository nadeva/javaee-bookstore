package com.oltruong.bookstore.view;

import com.oltruong.bookstore.model.Book;
import com.oltruong.bookstore.model.Order;
import com.oltruong.bookstore.model.OrderLine;
import com.oltruong.bookstore.service.BookService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.io.Serializable;
import java.util.List;

/**
 * Backing bean for OrderLine entities.
 * <p/>
 * This class provides CRUD functionality for all OrderLine entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class OrderLineBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "javaee-application-persistence-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Inject
    private BookService bookService;

    private Order order;

    public Order getOrder() {
        if (order == null) {
            order = new Order();
        }
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Inject
    private Conversation conversation;


    public String addBook(Book book) {

        if (conversation.isTransient()) {
            order = new Order();
            conversation.begin();

        }

        if (book != null) {
            OrderLine orderLine = findOrderLine(book.getId());
            if (orderLine != null) {
                orderLine.setQuantity(orderLine.getQuantity() + 1);
            } else {
                orderLine = new OrderLine();
                orderLine.setQuantity(1);
                orderLine.setBook(book);
                order.getOrderLines().add(orderLine);
            }
            order.setTotalOrder(order.getTotalOrder() + book.getPrice());
        }
        return "search";

    }

    private OrderLine findOrderLine(Long id) {
        List<OrderLine> orderLines = getOrder().getOrderLines();
        if (orderLines != null) {
            for (OrderLine orderLine : orderLines) {
                if (orderLine.getBook().getId().equals(id)) {
                    return orderLine;
                }
            }
        }
        return null;
    }


    public String sendOrder() {

        order = new Order();
        return null;
    }

    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    public OrderLine findById(Long id) {

        return this.entityManager.find(OrderLine.class, id);
    }


    @Resource
    private SessionContext sessionContext;

    public Converter getConverter() {

        final OrderLineBean ejbProxy = this.sessionContext.getBusinessObject(OrderLineBean.class);

        return new Converter() {

            @Override
            public Object getAsObject(FacesContext context,
                                      UIComponent component, String value) {

                return ejbProxy.findById(Long.valueOf(value));
            }

            @Override
            public String getAsString(FacesContext context,
                                      UIComponent component, Object value) {

                if (value == null) {
                    return "";
                }

                return String.valueOf(((OrderLine) value).getId());
            }
        };
    }

}