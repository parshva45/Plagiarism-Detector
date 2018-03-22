package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.Course;
import edu.northeastern.cs5500.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Praveen Singh
 * Service Class layer for Course
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Method to find a course by the given course id.
     * @param courseId Integer
     * @return Instance of Course
     */
    public Course getCourseById(Integer courseId){
        return courseRepository.findById(courseId);
    }
}
