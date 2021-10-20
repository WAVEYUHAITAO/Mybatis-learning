package com.hito.dao;

import com.hito.pojo.User;
import com.hito.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    @Test
    public void test(){
        //第一步：获取sqlSession对象,实际就是连接数据库
        //public interface SqlSession extends Closeable
        //JDK1.7之后有了try-with-resource处理机制。首先被自动关闭的资源需要实现Closeable或者AutoCloseable接口，因为只有实现了这两个接口才可以自动调用close()方法去自动关闭资源。写法为try(){}catch(){}，将要关闭的外部资源在try()中创建，catch()捕获处理异常。其实try-with-resource机制是一种语法糖，其底层实现原理仍然是try{}catch(){}finally{}写法，不过在catch(){}代码块中有一个addSuppressed()方法，即异常抑制方法。如果业务处理和关闭连接都出现了异常，业务处理的异常会抑制关闭连接的异常，只抛出处理中的异常，仍然可以通过getSuppressed()方法获得关闭连接的异常。
        //————————————————
        //版权声明：本文为CSDN博主「滴哩哩哩滴哩哩哩哒哒」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/weixin_42447959/article/details/81192098
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {

            //方式一：执行sql
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();

            //方式二：不推荐
            //List<User> userList = sqlSession.selectList("com.hito.dao.UserDao.getUserList");

            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getUserById(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user= mapper.getUserById(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserById2(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("userid",2);
            map.put("username","张三");
            User user= mapper.getUserById2(map);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.addUser(new User(4, "哈哈", "123123"));
            //mybatis里得增删改需要提交事务
            sqlSession.commit();
            if(i>0){
                System.out.println("插入成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUser2(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            Map<String, Object> map = new HashMap<>();
            map.put("userid",6);
            map.put("username","hello");
            map.put("password","1234567");

            int i = mapper.addUser2(map);
            //mybatis里得增删改需要提交事务
            sqlSession.commit();
            if(i>0){
                System.out.println("插入成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.updateUser(new User(4, "水水", "654321"));
            //mybatis里得增删改需要提交事务
            sqlSession.commit();
            if(i>0){
                System.out.println("修改成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUser(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int i = mapper.deleteUser(4);
            sqlSession.commit();
            if(i>0){
                System.out.println("删除成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据用户名字模糊查询
    @Test
    public void getUserByName(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> user = mapper.getUserByName("刘");
            sqlSession.commit();
            for (User user1 : user) {
                System.out.println(user1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
