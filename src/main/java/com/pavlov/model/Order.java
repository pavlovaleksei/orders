package com.pavlov.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    private String customername;
    private String customeraddr;
    private Double ordersum;
    private Timestamp createdate;

    public Order() {
    }

    public Order(long id, String customername, String customeraddr, Double ordersum, Timestamp createdate) {
        this.id = id;
        this.customername = customername;
        this.customeraddr = customeraddr;
        this.ordersum = ordersum;
        this.createdate = createdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeraddr() {
        return customeraddr;
    }

    public void setCustomeraddr(String customeraddr) {
        this.customeraddr = customeraddr;
    }

    public Double getOrdersum() {
        return ordersum;
    }

    public void setOrdersum(Double ordersum) {
        this.ordersum = ordersum;
    }

    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }
}
