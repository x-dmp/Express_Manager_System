package cn.edu.cuit.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * 对数据库客户密码进行加密
 */
public class Md5EncodingUtil {

    public static String encode(String data) {
        try {
            return DigestUtils.md5DigestAsHex(data.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
