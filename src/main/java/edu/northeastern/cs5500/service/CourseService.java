package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.Course;
import edu.northeastern.cs5500.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Praveen Singh
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course getCourseById(Integer courseId){
        return courseRepository.findById(courseId);
    }
}
