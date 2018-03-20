package edu.northeastern.cs5500.controllers.comparison;

import edu.northeastern.cs5500.service.FileComparisonService;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
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

    @InjectMocks
    private FileComparisonController fileComparisonController;

    @Test
    public void getSimilarityBetweenGivenFilesGivesExpectedSimilarity(){
        String firstFile = "praveen.txt";
        String secondFile = "singh.txt";
        String strategy = "demo";
        when(fileComparisonService
                .compareTwoFilesByGivenStrategy(strategy, firstFile, secondFile))
                .thenReturn(10.0);

        JSONObject jsonObject = fileComparisonController
                .getSimilarityBetweenGivenFiles(strategy, firstFile, secondFile);

        verify(fileComparisonService, times(1))
                .compareTwoFilesByGivenStrategy(strategy, firstFile, secondFile);
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
        when(fileComparisonService.getAllStrategies()).thenReturn(strategyTypes);

        JSONObject jsonObject = fileComparisonController.getListOfStrategies();

        assertNotNull(jsonObject);
        assertEquals("OK", jsonObject.get("response-code"));
        List<StrategyTypes> strategyTypes1 = (List<StrategyTypes>) jsonObject.get("strategies");
        assertEquals(strategyTypes.get(0), strategyTypes1.get(0));
    }

}