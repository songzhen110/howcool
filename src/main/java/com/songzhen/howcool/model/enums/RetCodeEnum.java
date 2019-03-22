package com.songzhen.howcool.model.enums;


public enum RetCodeEnum {
    /**
     * 错误编码
     */
    SUCCESS(0, "成功"),
    FAIL(-1, "成功");



    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    RetCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
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
    public static RetCodeEnum getDesc(Integer code) {
        for (RetCodeEnum errorCodeEnum : values()) {
            if (errorCodeEnum.getCode().equals(code)) {
                return errorCodeEnum;
            }
        }
        return null;
    }
}
