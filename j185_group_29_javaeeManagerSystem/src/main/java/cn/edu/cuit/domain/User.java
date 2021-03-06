package cn.edu.cuit.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 数据库用户实体类
 */
public class User {

    private Integer id;  // 用户id

    private String username;  // 用户名

    private String password;  // 用户密码

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 解决springMVC响应date为毫秒
    private Date joinTime;  // 注册时间

    private Integer power;  // 1表示管理员, 0表示普通用户

    private String avatar;  // 用户头像url

    private String address;  // 用户所在地

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

}
