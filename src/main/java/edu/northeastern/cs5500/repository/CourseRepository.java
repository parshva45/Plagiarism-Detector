package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Praveen Singh
 * Repository for Course
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    /**
     * Finds a course bt The ID.
     * @param id Integer
     * @return Instance of Course
     */
    Course findById(Integer id);
}
