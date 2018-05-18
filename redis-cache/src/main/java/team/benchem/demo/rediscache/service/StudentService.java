package team.benchem.demo.rediscache.service;

import team.benchem.demo.rediscache.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudent(String keyword);

    Student findStudent(String userName);

    Student appendStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(String userName);

}
