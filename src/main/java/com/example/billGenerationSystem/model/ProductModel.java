package com.example.billGenerationSystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product_info")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String name;

    private Double price;

    private int gst;

    private int stock;


    private int threshHold;

    @OneToMany(mappedBy = "product")
    private List<OrderModel> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getGst() {
        return gst;
    }

    public void setGst(int gst) {
        this.gst = gst;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getThreshHold() {
        return threshHold;
    }

    public void setThreshHold(int threshHold) {
        this.threshHold = threshHold;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
