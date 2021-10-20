package com.hito.dao;

import com.hito.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //获取全部用户
    List<User> getUserList();
    //根据ID查询用户
    User getUserById(int id);
    //插入用户
    int addUser(User user);
    //修改用户
    int updateUser(User user);
    //删除用户
    int deleteUser(int id);
    //分页实现
    List<User> getUserByLimit(Map<String,Integer> map);
    //分页2
    List<User> getUserByRowBounds();
}
