package com.example.controller;

import com.example.domain.Order;
import com.example.domain.Result;
import io.github.yedaxia.apidocs.Ignore;
import org.springframework.web.bind.annotation.*;

/**
 * @author chengdongyi
 * @description 订单模块
 * @date 2020/11/5 15:58
 */
@RequestMapping("/api/order/")
@RestController
public class OrderController {

    /**
     * 订单查询接口
     * @author chengdongyi
     * @date 2020/11/5 16:08
     * @param orderId 订单 Id
     * @return com.example.domain.Result<com.example.domain.Order>
     */
    @GetMapping("query")
    public Result<Order> query(@RequestParam String orderId){
        return null;
    }

    /**
     * 订单删除接口
     * @author chengdongyi
     * @date 2020/11/5 16:08
     * @param orderId 订单 Id
     * @return com.example.domain.Result<com.example.domain.Order>
     */
    @Ignore // 忽略该接口， 在Controller上，则忽略整个Controller
    @GetMapping("delete")
    public Result<Order> delete(@RequestParam String orderId){
        return null;
    }

}
