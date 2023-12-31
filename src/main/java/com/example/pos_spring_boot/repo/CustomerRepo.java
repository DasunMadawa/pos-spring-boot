package com.example.pos_spring_boot.repo;

import com.example.pos_spring_boot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer , String> {

}
