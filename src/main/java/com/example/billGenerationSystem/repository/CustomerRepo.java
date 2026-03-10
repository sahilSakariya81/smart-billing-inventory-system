package com.example.billGenerationSystem.repository;

import com.example.billGenerationSystem.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel,Integer> {
}
