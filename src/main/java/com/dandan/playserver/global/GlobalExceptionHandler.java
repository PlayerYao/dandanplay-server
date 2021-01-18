package com.dandan.playserver.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 *
 * @author JustYao
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param req http请求对象
     * @param e 异常对象
     * @return {@link ApiResult}
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ApiResult bizExceptionHandler(HttpServletRequest req, CustomException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ApiResult.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req http请求对象
     * @param e 异常对象
     * @return {@link ApiResult}
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ApiResult exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return ApiResult.error(ResultEnum.BODY_NOT_MATCH);
    }


    /**
     * 处理其他异常
     * @param req http请求对象
     * @param e 异常对象
     * @return {@link ApiResult}
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ApiResult exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return ApiResult.error(ResultEnum.UNKNOWN_ERROR);
    }
}
