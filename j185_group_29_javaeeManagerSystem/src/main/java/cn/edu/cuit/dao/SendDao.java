package cn.edu.cuit.dao;

import cn.edu.cuit.domain.Send;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SendDao {

    @Insert("INSERT INTO send(uuid, express, price, post_user, receive_user, company,position,send_time,from_address,to_address) VALUES(#{uuid}, #{express}, #{price}, #{postUser}, #{receiveUser}, #{company}, #{position}, #{sendTime}, #{fromAddress}, #{toAddress})")
        // 插入后自动注入用户id到send对象
    void saveSend(Send send);

    @Select("SELECT uuid, express, price, post_user, receive_user, company,position,send_time,from_address,to_address FROM send WHERE uuid = #{query}")
    List<Send> findByQuery(String query);

    @Select("SELECT uuid, express, price, post_user, receive_user, company,position,send_time,from_address,to_address FROM send")
    List<Send> find();

    @Update("UPDATE send set express = #{express}, price = #{price}, post_user = #{postUser}, receive_user = #{receiveUser}, "
            + "company = #{company},position = #{position}, send_time = #{sendTime},from_address = #{fromAddress},to_address = #{toAddress} WHERE uuid = #{uuid}")
    void modifySend(Send send);

    @Delete("DELETE FROM send WHERE uuid = #{uuid}")
    void deleteSend(String uuid);

    @Select("SELECT * FROM send WHERE uuid = #{uuid}")
    Send findSendById(Integer uuid);

}