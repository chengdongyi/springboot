package com.example.excel.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class OrderData {

    @ExcelProperty("订单号")
    private String orderId;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("订单状态")
    private String status;

    @ExcelProperty("订购时间")
    private Date orderTime;

}
