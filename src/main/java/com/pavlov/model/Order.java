package com.pavlov.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author Pavlov Aleksei
 * @Date 28-08-2020
 * Entity for orders table in Orders DB
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    private String customername;
    private String customeraddr;
    private Double ordersum;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "createdate")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(customername, order.customername) &&
                Objects.equals(customeraddr, order.customeraddr) &&
                Objects.equals(ordersum, order.ordersum) &&
                Objects.equals(createdate, order.createdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customername, customeraddr, ordersum, createdate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customername='" + customername + '\'' +
                ", customeraddr='" + customeraddr + '\'' +
                ", ordersum=" + ordersum +
                ", createdate=" + createdate +
                '}';
    }
}
