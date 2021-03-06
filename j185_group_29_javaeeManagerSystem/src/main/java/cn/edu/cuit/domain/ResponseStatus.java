package cn.edu.cuit.domain;

/**
 * 封装响应状态码及响应描述信息
 */
public class ResponseStatus {

    /**
     * 响应描述信息
     */
    private String msg;

    /**
     * 响应状态信息
     */
    private int code;

    public ResponseStatus() {
    }

    public ResponseStatus(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
