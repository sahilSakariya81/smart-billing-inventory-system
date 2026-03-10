package com.example.billGenerationSystem.model;


import jakarta.persistence.*;

@Entity
@Table(name = "oredr_info")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    private int quantity;


    @Column(name = "total_ampunt")
    private double totalAmount;

    public OrderModel(CustomerModel customer, ProductModel product, int quantity, double totalAmount) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
