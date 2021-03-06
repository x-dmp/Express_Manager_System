package cn.edu.cuit.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Company {

    private Integer id;  // 公司id

    private String name;  // 用户名

    private String introduce;  // 用户密码

    private String address;     //地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
