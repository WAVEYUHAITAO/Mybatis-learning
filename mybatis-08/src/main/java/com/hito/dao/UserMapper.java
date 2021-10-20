package com.hito.dao;

import com.hito.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User queryUserById(@Param("uid") int id);

    int updateUser(User user);
}
