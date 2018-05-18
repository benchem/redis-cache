package team.benchem.demo.rediscache.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team.benchem.demo.rediscache.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    Optional<Student> findByUserName(String userName);

    @Query("select s from Student s where s.userName like :keyword or s.nickName like :keyword order by s.registrationDate")
    List<Student> findAllByKeyword(@Param("keyword") String keyword);

}
