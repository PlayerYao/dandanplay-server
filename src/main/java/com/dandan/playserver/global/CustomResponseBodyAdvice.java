package com.dandan.playserver.global;

import cn.hutool.json.JSONUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * http全局返回
 *
* @author JustYao
 */
@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter returnType,@NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,@NonNull MethodParameter returnType, MediaType selectedContentType,@NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,@NonNull ServerHttpRequest request,@NonNull ServerHttpResponse response) {
        //如果是字符类型,输出json字符串
        if(selectedContentType.includes(MediaType.TEXT_HTML)||selectedContentType.includes(MediaType.TEXT_PLAIN)){
            return JSONUtil.toJsonStr(ApiResult.success(body.toString()));
        }
        //如果已经被异常捕获,返回的就是BaseResponse对象,不用再次封装了
        if (body instanceof ApiResult) {
            return body;
        }
        return ApiResult.success(body);
    }
}
