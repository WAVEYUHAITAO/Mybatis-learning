package com.hito.dao;

import com.hito.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {
    Teacher getTeacher(@Param("tid") int id);
}
