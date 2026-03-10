package com.example.billGenerationSystem.dtos;

import org.springframework.http.HttpStatus;

public class OrderResponseDTO {
    BillDTO billDTO;
    HttpStatus status;
    String msg;

    public OrderResponseDTO(){}
    public OrderResponseDTO(BillDTO billDTO, HttpStatus status, String msg) {
        this.billDTO = billDTO;
        this.status = status;
        this.msg = msg;
    }

    public BillDTO getBillDTO() {
        return billDTO;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
