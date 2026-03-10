package com.example.billGenerationSystem.controller;

import com.example.billGenerationSystem.dtos.OrderRequestDTO;
import com.example.billGenerationSystem.dtos.OrderResponseDTO;
import com.example.billGenerationSystem.model.CustomerModel;
import com.example.billGenerationSystem.service.CustomerService;
import com.example.billGenerationSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @PostMapping("regester")
    public String registerCust(@RequestBody CustomerModel model){
        customerService.registerCust(model);
        return "User Registed Successfully";
    }

    @PostMapping("placeOrder")
    public OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO dto){

       return orderService.placeOredr(dto);

    }

}
