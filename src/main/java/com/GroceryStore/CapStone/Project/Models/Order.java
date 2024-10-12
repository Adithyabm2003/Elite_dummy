package com.GroceryStore.CapStone.Project.Models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference // Change this to prevent recursion during serialization
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date order_date;

    private String order_status;

    private BigDecimal total_amount;

    // Constructors, Getters, Setters, etc.

    public Order() {}

    public Order(Customer customer, Date orderDate, String orderStatus, BigDecimal totalAmount) {
        this.customer = customer;
        this.order_date = orderDate;
        this.order_status = orderStatus;
        this.total_amount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return order_date;
    }

    public void setOrderDate(Date orderDate) {
        this.order_date = orderDate;
    }

    public String getOrderStatus() {
        return order_status;
    }

    public void setOrderStatus(String orderStatus) {
        this.order_status = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return total_amount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.total_amount = totalAmount;
    }
}
