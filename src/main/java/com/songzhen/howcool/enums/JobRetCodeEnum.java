package com.songzhen.howcool.enums;
/**
 * XXL-JOB执行码和执行备注.
 *
 * @author Lucas
 * @date 2019/3/9
 */
public enum JobRetCodeEnum {
    /**
     * JobRetCodeEnum
     */

    SUCCESS_CODE(200, "执行成功"), FAIL_CODE(500, "执行失败");

    private int value;
    private String title;

    JobRetCodeEnum(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public boolean is(int value) {
        return value == this.value;
    }

    public static boolean exists(int value) {
        JobRetCodeEnum[] values = JobRetCodeEnum.values();
        for (JobRetCodeEnum en : values) {
            if (en.is(value)) {
                return true;
            }
        }
        return false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}