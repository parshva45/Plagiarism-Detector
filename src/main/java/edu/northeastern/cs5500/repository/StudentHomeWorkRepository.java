package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.StudentHomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Praveen Singh
 */
@Repository
public interface StudentHomeWorkRepository extends JpaRepository<StudentHomeWork, Integer> {
    StudentHomeWork findByUserIdAndAndCourseIdAndHomeWorkId(Integer userId,
             Integer courseId, Integer homeWorkId);
    List<StudentHomeWork> findAllByUserId(Integer userId);
    List<StudentHomeWork> findAllByCourseIdAndAndHomeWorkId(Integer courseId, Integer homeWorkId);
}
