package com.example.domain;

import lombok.Data;

@Data
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

}
