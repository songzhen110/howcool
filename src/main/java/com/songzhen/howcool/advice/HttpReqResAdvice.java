package com.songzhen.howcool.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.songzhen.howcool.entity.HttpLogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

@ControllerAdvice
public class HttpReqResAdvice extends RequestBodyAdviceAdapter implements ResponseBodyAdvice<Object> {
    private static final Logger logger = LoggerFactory.getLogger(HttpReqResAdvice.class);

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpLogEntity httpLogEntity;

    /**
     * 开启对请求支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 开启对响应支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 在读到请求后记录请求
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        // 统一封装当前登陆人信息到请求body里
        JSONObject bodyJsonObject = JSONObject.parseObject(JSON.toJSONString(body));
        bodyJsonObject.put("currentUser",httpServletRequest.getAttribute("currentUser"));
        body = bodyJsonObject.toJavaObject(targetType);

        // 日志
        httpLogEntity.setPath(httpServletRequest.getServletPath());
        httpLogEntity.setParams(httpServletRequest.getParameterMap());
        httpLogEntity.setReq(body);
        return body;
    }

    /**
     * 在返回结果前记录返回
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        httpLogEntity.setRes(body);
        logger.info("http request info {}",JSON.toJSONString(httpLogEntity));
        return body;
    }


}
