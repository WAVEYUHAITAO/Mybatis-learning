import com.hito.dao.TeacherMapper;
import com.hito.pojo.Teacher;
import com.hito.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void test(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            List<Teacher> teacherList = mapper.getTeacher();
            for (Teacher teacher : teacherList) {
                System.out.println(teacher);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test1(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacherList = mapper.getTeacher(1);
            System.out.println(teacherList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacherList = mapper.getTeacher2(1);
            System.out.println(teacherList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
