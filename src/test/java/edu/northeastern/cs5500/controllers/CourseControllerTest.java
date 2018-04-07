package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.Course;
import edu.northeastern.cs5500.service.CourseService;
import edu.northeastern.cs5500.utils.Constants;
import org.json.simple.JSONObject;
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
public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @Test
    public void getCourseByIdShouldReturnValidCourse(){
        Course course = new Course().withId(1).withCourseName("MSD");
        Mockito.when(courseService.getCourseById(1)).thenReturn(course);

        JSONObject jsonObject = courseController.getCourseById(1);

        Mockito.verify(courseService, Mockito.times(1)).getCourseById(1);
        Assert.assertNotNull(jsonObject.get(Constants.RESPONSE_CODE));
        Assert.assertEquals("OK", jsonObject.get(Constants.RESPONSE_CODE));
        Assert.assertEquals(course, jsonObject.get(Constants.RESULT));
    }

}