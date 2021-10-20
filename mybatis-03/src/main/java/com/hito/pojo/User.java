package com.hito.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
//配置文件里别名配置扫描包的情况下，跟注解名字走
//@Alias("hello")
public class User {
    private int id;
    private String name;
    private String password;
}
