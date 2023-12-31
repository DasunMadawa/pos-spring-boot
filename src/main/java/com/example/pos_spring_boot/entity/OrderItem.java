package com.example.pos_spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
//@Data
//@ToString
@Getter
@Setter
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
