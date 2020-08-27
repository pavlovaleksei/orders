package com.pavlov.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderItem {

    private long id;
    private String customername;
    private String customeraddr;
    private Double ordersum;
    private Timestamp createdate;
    private List<OrderDetails> details;

    public OrderItem(long id, String customername, String customeraddr, Double ordersum, Timestamp createdate, List<OrderDetails> details) {
        this.id = id;
        this.customername = customername;
        this.customeraddr = customeraddr;
        this.ordersum = ordersum;
        this.createdate = createdate;
        this.details = details;
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

    public List<OrderDetails> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetails> details) {
        this.details = details;
    }
}
