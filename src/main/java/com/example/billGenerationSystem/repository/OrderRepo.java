package com.example.billGenerationSystem.repository;

import com.example.billGenerationSystem.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel,Integer> {


}
