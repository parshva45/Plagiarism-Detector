package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.HomeWork;
import edu.northeastern.cs5500.service.HomeWorkService;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeWorkControllerTest {

    @Mock
    private HomeWorkService homeWorkService;

    @InjectMocks
    private HomeWorkController homeWorkController;

    private HomeWork homeWork;

    @Before
    public void setUp(){
        homeWork = new HomeWork()
                .withId(1)
                .withCourseId(1)
                .withHomeWorkNumber(1)
                .withProfessorId(1)
                .withDescription("MSD");
    }

    @Test
    public void getListOfHomeWorksByCourse() {
        Mockito.when(homeWorkService.getListOfHomeWorkByCourseNo(1)).thenReturn(Arrays.asList(homeWork));

        JSONObject jsonObject = homeWorkController.getListOfHomeWorksByCourse(1);

        Mockito.verify(homeWorkService, Mockito.times(1)).getListOfHomeWorkByCourseNo(1);
        Assert.assertNotNull(jsonObject);
        Assert.assertNotNull(jsonObject.get("result"));
        List<HomeWork> homeWorkList = (List<HomeWork>) jsonObject.get("result");

        HomeWork homeWork1 = homeWorkList.get(0);

        Assert.assertEquals(homeWork, homeWork1);
        Assert.assertEquals(1, homeWork1.getId());
        Assert.assertEquals(1, homeWork1.getCourseId());
        Assert.assertEquals(1, homeWork1.getHomeWorkNumber());
        Assert.assertEquals(1, homeWork1.getProfessorId());
        Assert.assertEquals("MSD", homeWork1.getDescription());
    }

    @Test
    public void getHomeWorkById() {
        Mockito.when(homeWorkService.getHomeWorkById(1)).thenReturn(homeWork);

        JSONObject jsonObject = homeWorkController.getHomeWorkById(1);

        Mockito.verify(homeWorkService, Mockito.times(1)).getHomeWorkById( 1);
        Assert.assertNotNull(jsonObject);
        Assert.assertNotNull(jsonObject.get("result"));
        List<HomeWork> homeWorkList = (List<HomeWork>) jsonObject.get("result");
        HomeWork homeWork1 = homeWorkList.get(0);
        Assert.assertEquals(homeWork, homeWork1);
    }

    @Test
    public void getListOfHomeWorkByCourseAndHomeWorkNo() {
        Mockito.when(homeWorkService.getHomeWorkByCourseIdAndHomeWorkNumber(1, 1)).thenReturn(Arrays.asList(homeWork));

        JSONObject jsonObject = homeWorkController.getListOfHomeWorkByCourseAndHomeWorkNo(1, 1);

        Mockito.verify(homeWorkService, Mockito.times(1)).getHomeWorkByCourseIdAndHomeWorkNumber(1, 1);
        Assert.assertNotNull(jsonObject);
        Assert.assertNotNull(jsonObject.get("result"));
        List<HomeWork> homeWorkList = (List<HomeWork>) jsonObject.get("result");

        HomeWork homeWork1 = homeWorkList.get(0);

        Assert.assertEquals(homeWork, homeWork1);
        Assert.assertEquals(1, homeWork1.getId());
    }
}