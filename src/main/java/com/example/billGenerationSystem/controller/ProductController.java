package com.example.billGenerationSystem.controller;

import com.example.billGenerationSystem.model.ProductModel;
import com.example.billGenerationSystem.service.ProductService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("addAllProduct")
    public String addAllProducts(@RequestBody List<ProductModel>list){
        service.addAllProduct(list);
        return "Products are stored in Your WareHouse successfully";
    }

    @PostMapping("/sendMail")
    public String sendReport(){

        try{
            service.sendProductCsvToAdmin();
            return "Email Sent Successfully";
        }catch (MessagingException e){
           return e.getMessage();
        }

    }


}
