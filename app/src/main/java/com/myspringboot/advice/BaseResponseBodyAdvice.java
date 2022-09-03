package com.myspringboot.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspringboot.vo.BaseResult;
import com.myspringboot.vo.UzResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = {"com.myspringboot.controller"})
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final ObjectMapper DEFAULT = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        return returnType.hasMethodAnnotation(ResponseBody.class) && !returnType.getMethod().getReturnType().isAssignableFrom(UzResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof String) {
            return convertPojo2Json(new UzResult<>(body));
        }
        if (body instanceof BaseResult) {
            return body;
        }
        return new UzResult<>(body);
    }

    /**
     * 处理字符串返回的情况
     */
    public final static String convertPojo2Json(Object pojo) {
        try {
            return DEFAULT.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
