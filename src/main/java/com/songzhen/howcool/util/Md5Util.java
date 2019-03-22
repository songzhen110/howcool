package com.songzhen.howcool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 *
 * @author Lucas
 * @version 1.0
 * @date 2018/10/15 17:51
 **/
public class Md5Util {

    private static Logger logger = LoggerFactory.getLogger(Md5Util.class);

    public static String encodeMD5(String source) {

        StringBuffer sb = new StringBuffer(32);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            logger.error("Can not encode the string '" + source + "' to MD5!", e);
            return null;
        }

        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
        //测试
        logger.info(encodeMD5("666666"));
    }

}