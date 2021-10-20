package com.hito.dao;

import com.hito.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //获取全部用户
    List<User> getUserList();
    //根据ID查询用户
    User getUserById(int id);

    User getUserById2(Map<String,Object> map);
    //根据用户名模糊查询
    List<User> getUserByName(String name);

    //插入用户
    int addUser(User user);

    //万能的Map
    int addUser2(Map<String,Object> map);
    //修改用户
    int updateUser(User user);
    //删除用户
    int deleteUser(int id);
}
