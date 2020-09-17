package com.example.enums;

public enum ReturnCode {

    /* 成功状态码 */
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    BAD_REQUEST(400, "错误请求, Http 请求信息解析失败"),
    AUTH_ERROR(401, "认证失败"),
    NOT_FOUND(404, "网址不存在，请检查您访问的网址是否正确"),
    METHOD_NOT_ALLOWED(405, "不支持'%s'请求"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的 ContentType: '%s'"),
    UNKNOWN_ERROR(499, "未知错误"),
    SYS_ERROR(500, "系统繁忙, 请稍后再试!"),
    /* 参数错误: 1001-1999 */
    PARAM_ERROR(1001, "参数校验失败: "),
    PARAM_IS_INVALID(1002, "参数无效"),
    PARAM_IS_BLANK(1003, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1004, "参数类型错误"),
    PARAM_NOT_COMPLETE(1005, "参数缺失"),
    /* 用户错误: 2001-2999 */
    USER_NOT_LOGGED_IN(2001, "用户未登录, 访问的路径需要验证, 请登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户已存在");

    private Integer code;
    private String message;

    ReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
