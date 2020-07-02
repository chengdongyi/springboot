package com.example.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {

    private String orderId;

    private String mobile;

    private String productId;

    private String productName;

    private String price;

    private String status;

    private Date orderTime;

    private Date updateTime;

}
