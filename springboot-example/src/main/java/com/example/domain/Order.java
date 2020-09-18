package com.example.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author chengdongyi
 * @description:
 * @date 2020/9/16 15:40
 */
@Data
public class Order {

    @NotNull(message = "不能为空")
    @Length(min = 6, max = 20, message = "长度在6-20之间")
    private String orderId;

    @NotNull(message = "不能为空")
    private String mobile;

    private String orderTime;

}
