package team.benchem.demo.rediscache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import team.benchem.demo.rediscache.entity.Student;
import team.benchem.demo.rediscache.repository.StudentRepository;
import team.benchem.demo.rediscache.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudent(String keyword) {
        String queryWord = keyword == null || keyword == ""
                ? "%"
                : "%"+keyword+"%";
        return studentRepository.findAllByKeyword(queryWord);
    }

    @Cacheable(value = "StudentService:Student", key = "#userName")
    @Override
    public Student findStudent(String userName) {
        Optional<Student> studentOptional = studentRepository.findByUserName(userName);
        if(!studentOptional.isPresent()){
            return null;
        }
        return studentOptional.get();
    }

    @CachePut(value = "StudentService:Student", key = "#student.userName")
    @Override
    public Student appendStudent(Student student) {
        Student dbStudent = findStudent(student.getUserName());
        if(dbStudent != null){
            throw new RuntimeException("student name is exits");
        }
        return studentRepository.save(student);
    }

    @CachePut(value = "StudentService:Student", key = "#student.userName")
    @Override
    public Student updateStudent(Student student) {
        Student dbStudent = findStudent(student.getUserName());
        if(dbStudent == null){
            throw new RuntimeException("student is not exits");
        }
        if(!student.getRowId().equalsIgnoreCase(dbStudent.getRowId())){
            throw new RuntimeException("student name is exits");
        }
        return studentRepository.save(student);
    }

    @CacheEvict(value = "StudentService:Student", key = "#userName")
    @Override
    public void deleteStudent(String userName) {
        Student dbStudent = findStudent(userName);
        if(dbStudent == null){
            throw new RuntimeException("student is not exits");
        }
        studentRepository.delete(dbStudent);
    }
}
