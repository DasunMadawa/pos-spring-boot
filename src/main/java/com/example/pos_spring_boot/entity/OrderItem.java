package com.example.pos_spring_boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "order_items")
@IdClass(OrderItemsID.class)
public class OrderItem {
    @ManyToOne
    @Id
    private Order_t order_t;

    @ManyToOne
    @Id
    private Item item;

    private int qty;

}
