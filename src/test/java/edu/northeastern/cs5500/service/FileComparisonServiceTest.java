package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.SystemStatus;
import edu.northeastern.cs5500.repository.SystemStatusRepository;
import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.StrategyFactory;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class FileComparisonServiceTest {

    @Mock
    private StrategyFactory strategyFactory;

    @Mock
    private SimilarityStrategy similarityStrategy;

    @Mock
    private SystemStatusRepository systemStatusRepository;

    @InjectMocks
    private FileComparisonService fileComparisonService;

    @Test
    public void getAllCategoriesShouldReturnCorrectValues(){
        List<StrategyTypes> actual = fileComparisonService.getAllStrategies();
        StrategyTypes[] expected = {StrategyTypes.LEVENSHTEIN_DISTANCE, StrategyTypes.LCS,
                 StrategyTypes.AST_LCS, StrategyTypes.AST_TREE_EDIT_DISTANCE,
                StrategyTypes.WEIGHTED_SCORE, StrategyTypes.MOSS};
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    public void getSimilarityShouldReturnExpectedResult(){
        String file1 = "praveen";
        String file2 = "praveen";
        SystemStatus systemStatus = new SystemStatus().withScore(10.6);
        when(strategyFactory.getStrategyByStrategyType(null)).thenReturn(similarityStrategy);
        when(similarityStrategy.calculateSimilarity(file1, file2)).thenReturn(10.0);
        when(systemStatusRepository.save(systemStatus)).thenReturn(systemStatus);

        double res = fileComparisonService.compareTwoFilesByGivenStrategy(null, file1, file2);

        verify(similarityStrategy, times(1)).calculateSimilarity(file1, file2);
        verify(strategyFactory, times(1)).getStrategyByStrategyType(null);

        assertEquals(String.valueOf(10.0), String.valueOf(res));

    }

    @Test
    public void fileComparisonCountShouldReturnTheCount(){
        List<SystemStatus> list = new ArrayList<>();
        when(systemStatusRepository.findAllByCourseId(1)).thenReturn(list);

        int res = fileComparisonService.getCount();

        verify(systemStatusRepository, times(1)).findAllByCourseId(1);
        assertEquals(0, res);
    }

    @Test
    public void fileComparisonByAllShouldWork(){

        String file1 = "praveen";
        String file2 = "praveen";

        when(strategyFactory.getStrategyByStrategyType(anyString())).thenReturn(similarityStrategy);
        when(similarityStrategy.calculateSimilarity(file1, file2)).thenReturn(10.0);
        Map<String, Double> res = fileComparisonService.getSimilarityByAllMethods(file1, file2);
        assertNotNull(res);
    }




}