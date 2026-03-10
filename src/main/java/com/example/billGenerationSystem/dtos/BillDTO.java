package com.example.billGenerationSystem.dtos;

public class BillDTO {

    private String custName;
    private String productName;
    private double price;
    private int quantity;
    private double gstAmount;
    private double TotalAmount;

    public BillDTO(String name, String name1, Double price, int quantity, double gstAmount, double v) {
        this.custName = name;
        this.productName = name1;
        this.price = price;
        this.quantity = quantity;
        this.gstAmount = gstAmount;
        this.TotalAmount = v;
    }


    public String getCustName() {
        return custName;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getGst() {
        return gstAmount;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }
}
