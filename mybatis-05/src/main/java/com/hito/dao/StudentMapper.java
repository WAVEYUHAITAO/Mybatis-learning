package com.hito.dao;

import com.hito.pojo.Student;

import java.util.List;

public interface StudentMapper {

    //查询所有的学生信息，以及对应的老实的信息
    List<Student> getStudent();

    List<Student> getStudent2();
}
