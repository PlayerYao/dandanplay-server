package com.dandan.playserver.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一返回枚举类
 *
 * @author JustYao
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    /** 成功 */
    SUCCESS(20000,"成功"),
    /** 服务器异常 */
    SERVER_ERROR(10000,"服务器异常"),
    /** Json解析错误 */
    JSON_PARSE_ERROR(10003,"Json解析错误"),
    /** 非法字符串 */
    ILLEGAL_STRING(15001,"非法字符串"),
    /** 未知错误 */
    UNKNOWN_ERROR(16000,"未知错误"),
    /** 数据格式不符 */
    BODY_NOT_MATCH(12000,"请求的数据格式不符!"),
    /** 数据校验不通过 */
    DATA_VALIDATE_FAILD(12001,"数据校验不通过"),
    /** 找不到指定的文件 */
    FILE_NOT_FOUND(13000,"找不到指定的文件!"),
    //身份方面问题
    /** 请求过期 */
    REQUEST_OUT_OF_TIME(11001, "请求过期"),
    /** 身份认证失败 */
    AUTHENTICATION_FAILED(11002, "身份认证失败"),
    /** 请求类型不支持 */
    REQUEST_METHOD_ERROR(11003, "请求类型不支持"),
    /** 不支持此操作 */
    OPERATION_SCOPE_FAILED(11004, "不支持此操作");

    /** 返回码 */
    private int code;
    /** 信息 */
    private String msg;
}
