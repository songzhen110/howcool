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

    /**
    //@RequestBody @Validated JoinLocationShareVO param, HttpServletRequest request
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResponse jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        // 全局统一校验
        if(e instanceof MethodArgumentNotValidException ){
            MethodArgumentNotValidException  ex = (MethodArgumentNotValidException) e;

            BindingResult result = ex.getBindingResult();
            StringBuffer sb = new StringBuffer();

            for (FieldError error : result.getFieldErrors()) {
                String field = error.getField();
                String msg = error.getDefaultMessage();
                logger.warn(String.format("%s:%s ", field, msg));
                String message = String.format("【%s:%s】 ", field, "is error,please check");
                sb.append(message);
            }
            logger.warn("请求url:{},请求参数：{},错误的请求参数：{}", req.getRequestURL(),JSON.toJSONString(req.getParameterMap()),sb);
            return RestResponse.errorResp(MapErrorCode.PARAM_ERROR.getCode(),MapErrorCode.PARAM_ERROR.getMsg());
        }
        // 未知异常
        else {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    */


}
