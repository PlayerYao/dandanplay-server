package com.dandan.playserver.global;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户自定义异常
 *
 * @author JustYao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {
    /**
     * 错误码
     */
    private final int errorCode;
    /**
     * 错误信息
     */
    private final String errorMsg;

    public CustomException(ResultEnum resultEnum) {
        super(Integer.toString(resultEnum.getCode()));
        this.errorCode = resultEnum.getCode();
        this.errorMsg = resultEnum.getMsg();
    }

    public CustomException(ResultEnum resultEnum, Throwable cause) {
        super(Integer.toString(resultEnum.getCode()), cause);
        this.errorCode = resultEnum.getCode();
        this.errorMsg = resultEnum.getMsg();
    }

    public CustomException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        errorCode = ResultEnum.SERVER_ERROR.getCode();
    }

    public CustomException(int errorCode, String errorMsg) {
        super(Integer.toString(errorCode));
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CustomException(int errorCode, String errorMsg, Throwable cause) {
        super(Integer.toString(errorCode), cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
