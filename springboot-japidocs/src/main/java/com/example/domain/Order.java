package com.example.domain;

import io.github.yedaxia.apidocs.Ignore;
import lombok.Data;

@Data
public class Order {

    /**
     * 订单Id
     */
    private String orderId;

    /**
     * 产品Id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 订购时间
     */
    private String orderTime;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 证件号码
     */
    @Ignore
    private String idCard;

}
