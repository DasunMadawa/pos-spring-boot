package com.example.pos_spring_boot.repo;

import com.example.pos_spring_boot.entity.Order_t;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order_t , String> {

}
