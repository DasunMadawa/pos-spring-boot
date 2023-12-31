package com.example.pos_spring_boot.repo;

import com.example.pos_spring_boot.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item , String> {





}
