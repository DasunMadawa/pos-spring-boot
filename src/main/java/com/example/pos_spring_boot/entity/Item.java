package com.example.pos_spring_boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Item {
    @Id
    private String iCode;
    private String iName;
    private double iPrice;
    private int iQty;

    @OneToMany(mappedBy = "item" , cascade = CascadeType.ALL)
    private List<OrderItem> orders;

}
