package com.dandan.playserver.global;

import lombok.Data;


/**
 * 接口统一返回数据格式
 *
 * @author JustYao
 */
@Data
public class ApiResult {
    /** 状态码 */
    private int code;

    /** 返回信息 */
    private String msg;

    /** 返回数据 */
    private Object data;


    /**
     * 成功方法
     *
     * @param data 返回的数据
     * @return {@link ApiResult}
     **/
    public static ApiResult success(Object data) {
        ApiResult result = new ApiResult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }
    /**
     * 成功方法
     *
     * @param msg 返回信息
     * @return {@link ApiResult}
     **/
    public static ApiResult success(String msg) {
        return error(ResultEnum.SUCCESS.getCode(),msg);
    }
    /**
     * 默认成功方法
     *
     * @return {@link ApiResult}
     **/
    public static ApiResult success() {
        return success(null);
    }
    /**
     * 失败方法
     *
     * @param code 状态码
     * @param msg 返回的信息
     * @return {@link ApiResult}
     **/
    public static ApiResult error(Integer code, String msg) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    /**
     * 失败方法
     *
     * @param resultEnum 返回值枚举类对象
     * @return {@link ApiResult}
     **/
    public static ApiResult error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(),resultEnum.getMsg());
    }
}
