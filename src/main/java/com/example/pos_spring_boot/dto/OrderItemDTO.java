package com.example.pos_spring_boot.dto;

public class OrderItemDTO {
    private OrderDTO orderDTO;
    private ItemDTO itemDTO;
    private int qty;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderDTO orderDTO, ItemDTO itemDTO, int qty) {
        this.orderDTO = orderDTO;
        this.itemDTO = itemDTO;
        this.qty = qty;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
