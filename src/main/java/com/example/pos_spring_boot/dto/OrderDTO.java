package com.example.pos_spring_boot.dto;

import java.util.List;

public class OrderDTO {
    private String oId;
    private String oDate;
    private double oTotal;
    private double oSubTotal;
    private int oDiscount;
    private double oBalance;

    private List<ItemDTO> Items;
    private CustomerDTO customerDTO;

    public OrderDTO() {
    }

    public OrderDTO(String oId, String oDate, double oTotal, double oSubTotal, int oDiscount, double oBalance, List<ItemDTO> items, CustomerDTO customerDTO) {
        this.oId = oId;
        this.oDate = oDate;
        this.oTotal = oTotal;
        this.oSubTotal = oSubTotal;
        this.oDiscount = oDiscount;
        this.oBalance = oBalance;
        Items = items;
        this.customerDTO = customerDTO;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public double getoTotal() {
        return oTotal;
    }

    public void setoTotal(double oTotal) {
        this.oTotal = oTotal;
    }

    public double getoSubTotal() {
        return oSubTotal;
    }

    public void setoSubTotal(double oSubTotal) {
        this.oSubTotal = oSubTotal;
    }

    public int getoDiscount() {
        return oDiscount;
    }

    public void setoDiscount(int oDiscount) {
        this.oDiscount = oDiscount;
    }

    public double getoBalance() {
        return oBalance;
    }

    public void setoBalance(double oBalance) {
        this.oBalance = oBalance;
    }

    public List<ItemDTO> getItems() {
        return Items;
    }

    public void setItems(List<ItemDTO> items) {
        Items = items;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

}
