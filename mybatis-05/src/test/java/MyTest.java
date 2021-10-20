import com.hito.dao.StudentMapper;
import com.hito.dao.TeacherMapper;
import com.hito.pojo.Student;
import com.hito.pojo.Teacher;
import com.hito.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void test1(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacher = mapper.getTeacher(1);
            System.out.println(teacher);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> studentList = mapper.getStudent();
            for (Student student : studentList) {
                System.out.println(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()){
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> studentList = mapper.getStudent2();
            for (Student student : studentList) {
                System.out.println(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
