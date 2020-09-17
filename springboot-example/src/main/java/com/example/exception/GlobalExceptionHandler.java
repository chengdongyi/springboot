package com.example.exception;

import com.example.domain.Result;
import com.example.enums.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * @author chengdongyi
 * @description: 全局异常处理
 * @date 2020/9/11 10:48
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public Result handleBizException(BizException e) {
        log.error("异常信息: {}, {}", e.toString(), e.getStackTrace());
        return Result.fail(ReturnCode.FAIL, e.getMsg());
    }

    /**
     * 参数校验: 表单提交
     * public Result login(@Validated User user) {}
     * public Result login(@Valid User user) {}
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuffer sb = new StringBuffer();
        sb.append(ReturnCode.PARAM_ERROR.message());
        sb.append(bindingResult.getFieldError().getDefaultMessage());
        return Result.fail(ReturnCode.PARAM_ERROR, sb.toString());
    }

    /**
     * ValidationException
     */
//    @ExceptionHandler(ValidationException.class)
//    public RspDTO handleValidationException(ValidationException e) {
//        logger.error(e.getMessage(), e);
//        System.out.println("--------> 1234567");
//        return new RspDTO(VALIDATION_CODE, e.getCause().getMessage());
//    }

    /**
     * 参数校验: @requestParam/@PathVariable
     * 必须在Controller类上标注 @Validated 注解
     * public Result getUser(@NotNull(message = "不能为空") String username) {}
     *
     * @RequestMapping("/get/{username}") public Result getUser(@PathVariable("username") @NotNull(message = "不能为空") String username) {}
     * public Result getUser(@RequestParam("username") @NotNull(message = "不能为空") String username) {}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e) {

        String massage = e.getMessage();
        String path = massage.substring(0, massage.indexOf(".") + 1);
        massage = massage.replace(path, "");
        StringBuffer sb = new StringBuffer();
        sb.append(ReturnCode.PARAM_ERROR.message()).append(massage);
        return Result.fail(ReturnCode.PARAM_ERROR, sb.toString());
    }

    /**
     * 参数校验: @requestBody
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder(ReturnCode.PARAM_ERROR.message());
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append("; ");
        }
        return Result.fail(ReturnCode.PARAM_ERROR, sb.toString());
    }

    /**
     * HTTP请求解析异常
     * 例: 接收的参数为 Integer, 请求传递的参数为 String
     * HTTP返回码: 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return Result.fail(ReturnCode.BAD_REQUEST);
    }

    /**
     * 参数缺失
     * 例: public void login(@RequestParam("username") String username, String password) {}
     * 当请求参数 username 不存在时， 抛出 MissingServletRequestParameterException 异常
     * HTTP返回码: 400
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return Result.fail(ReturnCode.PARAM_NOT_COMPLETE);
    }

    /**
     * HTTP返回码: 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNoHandlerFoundException(NoHandlerFoundException e) {
        return Result.fail(ReturnCode.NOT_FOUND);
    }

    /**
     * 是否支持 GET、POST... 请求
     * HTTP返回码: 405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return Result.fail(ReturnCode.METHOD_NOT_ALLOWED, String.format(ReturnCode.METHOD_NOT_ALLOWED.message(), e.getMethod()));
    }

    /**
     * ContentType
     * HTTP返回码: 415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return Result.fail(ReturnCode.UNSUPPORTED_MEDIA_TYPE, String.format(ReturnCode.UNSUPPORTED_MEDIA_TYPE.message(), e.getContentType()));
    }

    /**
     * 重复提交
     */
//    @ExceptionHandler(ResubmitException.class)
//    public RspDTO handleResubmitException(ResubmitException e) {
//
//        logger.error(e.getMessage(), e);
//        return new RspDTO(Constant.RESUBMIT, e.getMessage());
//    }

//    @ExceptionHandler(DuplicateKeyException.class)
//    public RspDTO handleDuplicateKeyException(DuplicateKeyException e) {
//        logger.error(e.getMessage(), e);
//        return new RspDTO(DUPLICATE_KEY_CODE, "数据重复,请检查后提交");
//    }


    /**
     * 所有异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("异常信息: {}, {}", e.toString(), e.getStackTrace());
        return Result.fail(ReturnCode.SYS_ERROR);
    }

}
