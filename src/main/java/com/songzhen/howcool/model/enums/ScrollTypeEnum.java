package com.songzhen.howcool.model.enums;

/**
 * @author lucas
 */

public enum ScrollTypeEnum {

    PRE(1),

    COMMON(2);

    private Integer type;

    ScrollTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
