package cn.edu.cuit.utils;

import cn.edu.cuit.domain.ResponseStatus;
import cn.edu.cuit.domain.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * 响应工具类, 使返回字符串再json格式化前呈现如下格式
     {
        "data": {
            "username": "77"
        },
        "meta": {
            "msg": "",
            "status": 200
        }
     }
 */
public class ResponseInfoUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 接收数据，返回json格式字符串
     * @param data 真实响应数据类型
     * @param msg 响应描述信息
     * @param code 响应状态码
     * @return
     */
    public static HashMap<String, Object> responseDataAndMeta(Object data, String msg, HttpStatus code) {
        HashMap<String, Object> responseInfo = new HashMap<>();
        responseInfo.put("data", data);
        ResponseStatus responseStatus = new ResponseStatus(msg, Integer.parseInt(code.toString()));
        responseInfo.put("meta", responseStatus);

        return responseInfo;
    }

}
