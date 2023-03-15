package com.songzhen.howcool.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})//使用位置 ElementType.TYPE 代表：类、接口、枚举、注解 ElementType.METHOD 代表：方法
@Retention(RetentionPolicy.RUNTIME)//保留策略 RetentionPolicy.RUNTIME 代表： 源码、字节码、运行期间都存在
public @interface NeedLogin {
    boolean required() default true;
}