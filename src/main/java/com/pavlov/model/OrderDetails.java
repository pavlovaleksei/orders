package com.pavlov.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "details")
public class OrderDetails {


    private Long detailid;
    private String productserialnum;
    private String productname;
    private Long count;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="orders_id")
    private Long orderid;

    public Long getDetailid() {
        return detailid;
    }

    public void setDetailid(Long detailid) {
        this.detailid = detailid;
    }

    public String getProductserialnum() {
        return productserialnum;
    }

    public void setProductserialnum(String productserialnum) {
        this.productserialnum = productserialnum;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(detailid, that.detailid) &&
                Objects.equals(productserialnum, that.productserialnum) &&
                Objects.equals(productname, that.productname) &&
                Objects.equals(count, that.count) &&
                Objects.equals(orderid, that.orderid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailid, productserialnum, productname, count, orderid);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "detailid=" + detailid +
                ", productserialnum='" + productserialnum + '\'' +
                ", productname='" + productname + '\'' +
                ", count=" + count +
                ", orderid=" + orderid +
                '}';
    }
}
