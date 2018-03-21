package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.Course;
import edu.northeastern.cs5500.repository.CourseRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test()
    public void testCourse(){
        Course course = new Course().withId(1).withCourseName("MSD");
        Mockito.when(courseRepository.findById(1)).thenReturn(course);

        Course course1 = courseService.getCourseById(1);

        Mockito.verify(courseRepository, Mockito.times(1)).findById(1);
        Assert.assertEquals(1, course1.getId());
        Assert.assertEquals("MSD", course1.getCourseName());

    }

}