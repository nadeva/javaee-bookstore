package com.oltruong.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "myOrder")
@NamedQueries({
        @NamedQuery(name = Order.FIND_ALL, query = "SELECT o FROM Order o")
})
public class Order implements Serializable {

    public static final String FIND_ALL = "Order.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    private Float totalOrder = 0F;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private Date creationDate;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Order)) {
            return false;
        }
        Order other = (Order) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public List<OrderLine> getOrderLines() {
        return this.orderLines;
    }

    public void setOrderLines(final List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Float getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Float total) {
        this.totalOrder = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreationDate() {

        if (creationDate == null) {
            creationDate = new Date();
        }

        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (id != null)
            result += "id: " + id;
        result += ", version: " + version;
        if (orderLines != null)
            result += ", orderLines: " + orderLines;
        if (totalOrder != null)
            result += ", totalOrder: " + totalOrder;
        if (name != null && !name.trim().isEmpty())
            result += ", name: " + name;
        if (address != null && !address.trim().isEmpty())
            result += ", address: " + address;
        if (creationDate != null)
            result += ", creationDate: " + creationDate;
        return result;
    }
}