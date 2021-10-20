import com.hito.dao.UserMapper;
import com.hito.pojo.User;
import com.hito.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void test() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //在一个sqlsession里查询两次相同的sql， sql语句只执行了一次
            User user1 = mapper.queryUserById(1);
            System.out.println(user1);
            System.out.println("============================");
            User user2 = mapper.queryUserById(1);
            System.out.println(user2);
            System.out.println(user1 == user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //在一个sqlsession里查询两次不相同的sql， sql语句执行了2次
            User user1 = mapper.queryUserById(1);
            System.out.println(user1);
            System.out.println("============================");
            User user2 = mapper.queryUserById(2);
            System.out.println(user2);
            System.out.println(user1 == user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //在一个sqlsession里查询两次相同的sql， sql语句只执行了一次
            User user1 = mapper.queryUserById(1);
            System.out.println(user1);
            //这里执行一个update语句，则缓存被刷新，所以user2也需要再执行一次sql语句，任意增删改都会刷新缓存
            //增删改会更改数据库内容，所以缓存会失效
            mapper.updateUser(new User(2, "aaa", "bbb"));
            System.out.println("============================");
            User user2 = mapper.queryUserById(1);
            System.out.println(user2);
            //两次sql语句查询出来的user不再是同一个user
            System.out.println(user1 == user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //在一个sqlsession里查询两次相同的sql， sql语句只执行了一次
            User user1 = mapper.queryUserById(1);
            System.out.println(user1);
            //这里执行一个update语句，则缓存被刷新，所以user2也需要再执行一次sql语句，任意增删改都会刷新缓存
            //增删改会更改数据库内容，所以缓存会失效
            //mapper.updateUser(new User(2,"aaa","bbb"));
            sqlSession.clearCache(); //清理缓存，一级缓存默认开启，只再一次 sqlsession中有效，也就是拿到连接到关闭连接
            System.out.println("============================");
            User user2 = mapper.queryUserById(1);
            System.out.println(user2);
            //两次sql语句查询出来的user不再是同一个user
            System.out.println(user1 == user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);

        User user1 = mapper.queryUserById(1);
        System.out.println(user1);

        System.out.println("============================");

        User user2 = mapper2.queryUserById(1);
        System.out.println(user2);

        //两次sql语句查询出来的user不再是同一个user
        System.out.println(user1 == user2);

        sqlSession.close();
        sqlSession2.close();
    }
    //二级缓存的测试
    //二级缓存是事务性的。这意味着，当sqlsession完成并提交时，或是完成并回滚，但是没有执行flushCache=true的insert/delete/update语句时，二级缓存会得到更新
    //这个例子第一个sql close后，一级缓存内容失效，并保存至二级缓存，下一个sqlsession查询就可以直接从二级缓存查询得到结果，而不用执行sql语句
    //查询时，先看二级缓存有没有，再看一级缓存，都没有再查询数据库
    @Test
    public void test6() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);

        User user1 = mapper.queryUserById(1);
        System.out.println(user1);
        sqlSession.close();
        System.out.println("============================");

        User user2 = mapper2.queryUserById(1);
        System.out.println(user2);

        //两次sql语句查询出来的user不再是同一个user
        System.out.println(user1 == user2);


        sqlSession2.close();
    }
}
