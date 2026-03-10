package com.example.billGenerationSystem.service;

import com.example.billGenerationSystem.model.CustomerModel;
import com.example.billGenerationSystem.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public void registerCust(CustomerModel model) {
        customerRepo.save(model);
    }
}
