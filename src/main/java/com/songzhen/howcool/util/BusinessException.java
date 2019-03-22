package com.songzhen.howcool.util;

/**
 * @desc 业务异常类
 * 
 * @author zhumaer
 * @since 9/18/2017 3:00 PM
 */

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 194906846739586856L;

    protected String code;

    protected String message;

    public BusinessException(String message) {
        super();
        this.message = message;
    }



    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
