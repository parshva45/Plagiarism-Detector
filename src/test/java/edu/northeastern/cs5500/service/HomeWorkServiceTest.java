package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.HomeWork;
import edu.northeastern.cs5500.repository.HomeWorkRepository;
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

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeWorkServiceTest {

    @Mock
    private HomeWorkRepository homeWorkRepository;

    @InjectMocks
    private HomeWorkService homeWorkService;

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
    public void getListOfHomeWorkByCourseNo() {
        Mockito.when(homeWorkRepository.findByCourseId(1)).thenReturn(Arrays.asList(homeWork));

        List<HomeWork> homeWorkList = homeWorkService.getListOfHomeWorkByCourseNo(1);

        Mockito.verify(homeWorkRepository, Mockito.times(1)).findByCourseId(1);
        Assert.assertNotNull(homeWorkList);
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
        Mockito.when(homeWorkRepository.findById(1)).thenReturn(homeWork);

        HomeWork result = homeWorkService.getHomeWorkById(1);

        Assert.assertEquals(homeWork, result);
        Assert.assertEquals(1, result.getId());
    }

    @Test
    public void getHomeWorkByCourseIdAndHomeWorkNumber() {
        Mockito.when(homeWorkRepository.findByCourseIdAndHomeWorkNumber(1, 1)).thenReturn(Arrays.asList(homeWork));

        List<HomeWork> homeWorkList = homeWorkService.getHomeWorkByCourseIdAndHomeWorkNumber(1, 1);

        Mockito.verify(homeWorkRepository, Mockito.times(1)).findByCourseIdAndHomeWorkNumber(1, 1);
        Assert.assertNotNull(homeWorkList);
        HomeWork homeWork1 = homeWorkList.get(0);

        Assert.assertEquals(homeWork, homeWork1);
    }
}