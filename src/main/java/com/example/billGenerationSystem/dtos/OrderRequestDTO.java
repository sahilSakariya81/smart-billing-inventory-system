package com.example.billGenerationSystem.dtos;

import org.aspectj.weaver.ast.Or;

public class OrderRequestDTO {
    private int cId;
    private int pId;
    private int quantity;

    public OrderRequestDTO(){}
    public OrderRequestDTO(int quantity, int pId, int cId) {
        this.quantity = quantity;
        this.pId = pId;
        this.cId = cId;
    }

    public int getcId() {
        return cId;
    }

    public int getpId() {
        return pId;
    }

    public int getQuantity() {
        return quantity;
    }
}
