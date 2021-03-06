package cn.edu.cuit.dao;

import cn.edu.cuit.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Insert("INSERT INTO user(username, password, join_time, power, address) VALUES(#{username}, #{password}, #{joinTime}, #{power}, #{address})")
    @Options(useGeneratedKeys=true, keyProperty="id")  // 插入后自动注入用户id到user对象
    void saveUser(User user);

    @Select("SELECT id, username, join_time, power, avatar, address FROM user WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);  // 多个参数使用@Param

    @Select("SELECT id, username, join_time, power, avatar, address FROM user WHERE username LIKE #{query}")
    List<User> findByQuery(String query);

    @Update("UPDATE user set username = #{username}, address = #{address}, power = #{power} WHERE id = #{id}")
    void modifyUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(Integer id);

    @Select("SELECT id, username, join_time, power, avatar, address FROM user WHERE id = #{id}")
    User findUserById(Integer id);

    @Update("UPDATE user set avatar = #{url} WHERE id = #{id}")
    void modifyUserAvatarUrl(@Param("id") Integer id,@Param("url") String url);
}
