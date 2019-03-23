package com.songzhen.howcool.model.enums;

/**
 * 返回对象.
 *
 * @author Lucas
 * @date 2019/3/18
 */
public enum RetCodeEnum {
    /**
     * 错误编码
     */
    SUCCESS("00", "成功"),
    ACCOUNT_UNAUTHORIZED("401", "未授权：请登录");



    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String desc;

    RetCodeEnum(){

    }

    RetCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 获取描述信息
     *
     * @param code 编码
     * @return 描述信息
     */
    public static RetCodeEnum getDesc(String code) {
        for (RetCodeEnum errorCodeEnum : values()) {
            if (errorCodeEnum.getCode().equals(code)) {
                return errorCodeEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "RetCodeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
