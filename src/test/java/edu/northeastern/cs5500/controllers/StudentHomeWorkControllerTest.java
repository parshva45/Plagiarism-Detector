package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.service.StudentHomeWorkService;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentHomeWorkControllerTest {

    @Mock
    private StudentHomeWorkService studentHomeWorkService;

    @InjectMocks
    private StudentHomeWorkController studentHomeWorkController;

    @Test
    public void getHomeWorksForStudentShouldReturnProperResult(){
        StudentHomeWork studentHomeWork = new StudentHomeWork()
                .withCourseId(1).withUserId(1).withHomeWorkId(1).withPath("praveen");
        List<StudentHomeWork> list = new ArrayList<>();
        list.add(studentHomeWork);
        Mockito.when(studentHomeWorkService.getListOfHomeWorksByStudentId(1)).thenReturn(list);

        JSONObject jsonObject = studentHomeWorkController.getHomeWorksForStudent(1);

        Assert.assertEquals("OK", jsonObject.get("response-code"));
        Assert.assertEquals(list, jsonObject.get("result"));
    }
    
    @Test
    public void getStudentHomeworksForCourseHomeWorkShouldReturnProperResult(){
        StudentHomeWork studentHomeWork1 = new StudentHomeWork()
                .withCourseId(1).withUserId(1).withHomeWorkId(1).withPath("praveen");
        StudentHomeWork studentHomeWork2 = new StudentHomeWork()
                .withCourseId(1).withUserId(2).withHomeWorkId(1).withPath("parshva");
        List<StudentHomeWork> list = new ArrayList<>();
        list.add(studentHomeWork1);
        list.add(studentHomeWork2);
        Mockito.when(studentHomeWorkService.getListOfHomeWorksByCourseIdAndHomeWorkId(1,1)).thenReturn(list);

        JSONObject jsonObject = studentHomeWorkController.getStudentHomeworksForCourseHomeWork(1,1);

        Assert.assertEquals("OK", jsonObject.get("response-code"));
        Assert.assertEquals(list, jsonObject.get("result"));
    }

    @Test
    public void submitHomeWorkShouldWorkAsExpected(){
        StudentHomeWork studentHomeWork = new StudentHomeWork()
                .withCourseId(1).withUserId(1).withHomeWorkId(1).withPath("praveen");
        Mockito.when(studentHomeWorkService.submitHomeWork(1, 1, 1, "praveen"))
                .thenReturn(studentHomeWork);

        JSONObject jsonObject = studentHomeWorkController.submitHomeWork("praveen", 1, 1, 1);

        Assert.assertEquals("OK", jsonObject.get("response-code"));
        Assert.assertEquals(studentHomeWork, jsonObject.get("result"));
    }

}