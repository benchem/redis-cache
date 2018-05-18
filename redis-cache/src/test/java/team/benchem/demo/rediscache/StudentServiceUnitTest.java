package team.benchem.demo.rediscache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.benchem.demo.rediscache.entity.Student;
import team.benchem.demo.rediscache.service.StudentService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceUnitTest {

    @Autowired
    StudentService studentService;

    @Before
    public void before(){

    }

    @After
    public void after(){

    }

    @Test
    public void test_cache_case1(){

        Student student = new Student();
        student.setUserName("admin");
        student.setNickName("AAA");
        studentService.appendStudent(student);

        Student dbStudent = studentService.findStudent("admin");
        Assert.assertNotNull(dbStudent);

        student.setNickName("BBB");
        studentService.updateStudent(student);

        Student dbStudent2 = studentService.findStudent("admin");
        Assert.assertEquals(dbStudent2.getNickName(), "BBB");

    }

    @Test
    public void test_cache_case2(){

        Student student = new Student();
        student.setUserName("user1");
        student.setNickName("AAA");
        studentService.appendStudent(student);

        Student dbStudent = studentService.findStudent("user1");
        Assert.assertNotNull(dbStudent);

        studentService.deleteStudent("user1");

        Student dbStudent2 = studentService.findStudent("user1");
        Assert.assertNull(dbStudent2);

    }

}
