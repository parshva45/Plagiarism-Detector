package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.CompareAllService;
import edu.northeastern.cs5500.service.FileComparisonService;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import edu.northeastern.cs5500.utils.Constants;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class FileComparisonControllerTest {

    @Mock
    private FileComparisonService fileComparisonService;

    @Mock
    private CompareAllService compareAllService;

    @Mock
    private Environment env;

    @InjectMocks
    private FileComparisonController fileComparisonController;

    @Test
    public void getSimilarityBetweenGivenFilesGivesExpectedSimilarity(){
        String firstFile = "praveen.txt";
        String secondFile = "singh.txt";
        String strategy = "demo";
        when(env.getProperty(Constants.PLAGIARISM_THRESHOLD)).thenReturn("40.0");
        when(fileComparisonService
                .compareTwoFilesByGivenStrategy(strategy, firstFile, secondFile))
                .thenReturn(10.0);

        JSONObject jsonObject = fileComparisonController
                .getSimilarityBetweenGivenFiles(strategy, firstFile, secondFile);

        verify(fileComparisonService, times(1))
                .compareTwoFilesByGivenStrategy(strategy, firstFile, secondFile);
        verify(env, times(1)).getProperty(Constants.PLAGIARISM_THRESHOLD);

        assertNotNull(jsonObject);
        assertEquals(firstFile, jsonObject.get("firstFile"));
        assertEquals(secondFile, jsonObject.get("secondFile"));
        assertEquals(strategy, jsonObject.get("strategy"));
        assertEquals("OK", jsonObject.get("response-code"));
        assertEquals(String.valueOf(10.0), jsonObject.get("similarity"));
    }

    @Test
    public void getAllServicesShouldReturnAllList(){
        List<StrategyTypes> strategyTypes = new ArrayList<>();
        strategyTypes.add(StrategyTypes.LEVENSHTEIN_DISTANCE);
        strategyTypes.add(StrategyTypes.LCS);
        when(fileComparisonService.getAllStrategies()).thenReturn(strategyTypes);

        JSONObject jsonObject = fileComparisonController.getListOfStrategies();

        assertNotNull(jsonObject);
        assertEquals("OK", jsonObject.get("response-code"));
        List<StrategyTypes> strategyTypes1 = (List<StrategyTypes>) jsonObject.get("strategies");
        assertEquals(strategyTypes.get(0), strategyTypes1.get(0));
    }

    @Test
    public void getCountShouldReturnTheProperCount(){
        when(fileComparisonService.getCount()).thenReturn(1);

        int count = fileComparisonController.getCount();

        verify(fileComparisonService, times(1)).getCount();
        assertEquals(1, count);
    }

    @Test
    public void getSimilarityByAll(){
        String firstFile = "praveen.txt";
        String secondFile = "singh.txt";
        when(fileComparisonService.getSimilarityByAllMethods(firstFile, secondFile)).thenReturn(new HashMap<>());

        fileComparisonController.getSimilarityBetweenGivenFilesByAllStrategies(firstFile, secondFile);

        verify(fileComparisonService, times(1)).getSimilarityByAllMethods(firstFile, secondFile);

    }

    @Test
    public void getComparisonByAllShouldRunAsExpected(){
        Integer userId = 1;
        doNothing().when(compareAllService).process(userId);
        JSONObject jsonObject = fileComparisonController.asyncCompareAll(userId);
        verify(compareAllService, times(1)).process(userId);
        assertNotNull(jsonObject);
        assertEquals("OK", jsonObject.get("status"));
    }

}