package cn.edu.cuit.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 数据库快递实体类
 */
public class Send {

    private String uuid;  // 快递id

    private Integer express;    //商品id

    private Double price;  // 快递价格

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 解决springMVC响应date为毫秒
    private Date sendTime;  // 发货时间
    
    private Integer postUser;//发货方id

    private Integer receiveUser;//收货方id
    
    private Integer company;//快递公司id
    
    private String position;//位置信息 


    private String fromAddress;  // 发出地
    private String toAddress;  // 收货地

    private User pUser;  // 寄件人
    private User rUser;  // 收件人
    private Company eCompany;  // 寄件公司
    private Express ex;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getExpress() {
        return express;
    }

    public void setExpress(Integer express) {
        this.express = express;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getPostUser() {
        return postUser;
    }

    public void setPostUser(Integer postUser) {
        this.postUser = postUser;
    }

    public Integer getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(Integer receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public User getpUser() {
        return pUser;
    }

    public void setpUser(User pUser) {
        this.pUser = pUser;
    }

    public User getrUser() {
        return rUser;
    }

    public void setrUser(User rUser) {
        this.rUser = rUser;
    }

    public Company geteCompany() {
        return eCompany;
    }

    public void seteCompany(Company eCompany) {
        this.eCompany = eCompany;
    }

    public Express getEx() {
        return ex;
    }

    public void setEx(Express ex) {
        this.ex = ex;
    }
}