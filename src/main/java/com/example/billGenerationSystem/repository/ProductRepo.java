package com.example.billGenerationSystem.repository;

import com.example.billGenerationSystem.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepo extends JpaRepository<ProductModel,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_info SET stock = stock - ?1 where product_id = ?2",nativeQuery = true)
    void updateStock(int qua,int pid);

    @Query(value = "SELECT stock from product_info WHERE product_id = ?1",nativeQuery = true)
    int getCurrentStock(int pid);
}
