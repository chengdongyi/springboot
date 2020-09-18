package com.example.domain;

import com.example.enums.ReturnCode;
import lombok.Data;

@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public Result() {

    }

    public Result(ReturnCode returnCode) {
        this.code = returnCode.code();
        this.message = returnCode.message();
    }

    public Result(ReturnCode returnCode, T data) {
        this.code = returnCode.code();
        this.message = returnCode.message();
        this.data = data;
    }

    public static Result success() {
        return new Result(ReturnCode.SUCCESS);
    }

    public static <T> Result success(T data) {
        return new Result(ReturnCode.SUCCESS, data);
    }

    public static Result fail(ReturnCode returnCode) {
        return new Result(returnCode);
    }

    public static Result fail(ReturnCode returnCode, String message) {
        Result result = new Result(returnCode);
        result.setMessage(message);
        return result;
    }

}
