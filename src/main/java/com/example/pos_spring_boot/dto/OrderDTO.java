package com.example.pos_spring_boot.dto;

import java.util.List;

public class OrderDTO {
    private String oId;
    private String oDate;
    private double oTotal;
    private double oSubTotal;
    private int oDiscount;
    private double oBalance;

    private List<OrderItemDTO> orderItemDTOList;
    private CustomerDTO customerDTO;

    public OrderDTO() {
    }

    public OrderDTO(String oId, String oDate, double oTotal, double oSubTotal, int oDiscount, double oBalance, List<OrderItemDTO> orderItemDTOList, CustomerDTO customerDTO) {
        this.oId = oId;
        this.oDate = oDate;
        this.oTotal = oTotal;
        this.oSubTotal = oSubTotal;
        this.oDiscount = oDiscount;
        this.oBalance = oBalance;
        this.orderItemDTOList = orderItemDTOList;
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

    public List<OrderItemDTO> getOrderItemDTOList() {
        return orderItemDTOList;
    }

    public void setOrderItemDTOList(List<OrderItemDTO> orderItemDTOList) {
        this.orderItemDTOList = orderItemDTOList;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

}
