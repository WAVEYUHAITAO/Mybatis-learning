import com.hito.dao.BlogMapper;
import com.hito.pojo.Blog;
import com.hito.utils.IDUtils;
import com.hito.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyTest {
    @Test
    public void addBlogTest() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession();) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setId(IDUtils.getId());
            blog.setTitle("Mybatis");
            blog.setAuthor("狂神说");
            blog.setCreateTime(new Date());
            blog.setViews(9999);

            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("Java");
            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("Spring");
            mapper.addBlog(blog);

            blog.setId(IDUtils.getId());
            blog.setTitle("微服务");
            mapper.addBlog(blog);
        }


    }

    @Test
    public void test1(){
        try(SqlSession sqlSession=MybatisUtils.getSqlSession()){
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            HashMap map = new HashMap<>();
            //map.put("title","微服务");
            map.put("author","狂神说");
            List<Blog> blogList = mapper.queryBlogIF(map);
            for (Blog blog : blogList) {
                System.out.println(blog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try(SqlSession sqlSession=MybatisUtils.getSqlSession()){
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            HashMap map = new HashMap<>();
            map.put("title","微服务");
            map.put("author","狂神说");
            map.put("views",9999);
            List<Blog> blogList = mapper.queryBlogChoose(map);
            for (Blog blog : blogList) {
                System.out.println(blog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        try(SqlSession sqlSession=MybatisUtils.getSqlSession()){
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            HashMap map = new HashMap<>();
            //map.put("title","微服务");
            map.put("author","海涛1");
            map.put("id","01d6288deef64c8fbcb2dde5b8b3ace3");
            int i = mapper.updateBlog(map);
            if(i>0){
                System.out.println("更改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        try(SqlSession sqlSession=MybatisUtils.getSqlSession()){
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            HashMap map = new HashMap<>();
            ArrayList<Object> ids = new ArrayList<>();
            ids.add("1");
            ids.add("2");
            ids.add("3");
            map.put("ids",ids);

            List<Blog> blogList = mapper.queryBlogForeach(map);
            for (Blog blog : blogList) {
                System.out.println(blog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
