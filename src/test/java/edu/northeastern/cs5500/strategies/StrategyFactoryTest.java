package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.strategies.implementations.FileMetaData;
import edu.northeastern.cs5500.strategies.implementations.LCS;
import edu.northeastern.cs5500.strategies.implementations.LevenshteinDistance;
import edu.northeastern.cs5500.strategies.implementations.ast.lcs.LongestCommonSubSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Praveen Singh
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyFactoryTest{

    @Autowired
    LevenshteinDistance levenshteinDistance;

    @Autowired
    LCS lcs;
    
    @Autowired
    LongestCommonSubSequence ast_lcs;

    @Autowired
    StrategyFactory strategyFactory;

    @Autowired
    FileMetaData fileMetaData;


    /**
     * test for getting levenshtein distance strategy when it is provided
     */
    @Test
    public void getLevenshteinDistanceStrategyShouldReturnTheExpectedStrategy(){
        Assert.assertEquals(levenshteinDistance,
                strategyFactory.getStrategyByStrategyType("LEVENSHTEIN_DISTANCE"));
    }
    
    /**
     * test for getting lcs strategy when it is provided
     */
    @Test
    public void getLCSStrategyShouldReturnTheExpectedStrategy(){
        Assert.assertEquals(lcs,
                strategyFactory.getStrategyByStrategyType("LCS"));
    }
    
    /**
     * test for getting AST lcs strategy when it is provided
     */
    @Test
    public void getASTLCSStrategyShouldReturnTheExpectedStrategy(){
        Assert.assertEquals(ast_lcs,
                strategyFactory.getStrategyByStrategyType("AST_LCS"));
    }

    /**
     * test for getting levenshtein distance strategy when invalid strategy is provided
     */
    @Test
    public void getLevenshteinDistanceStrategyShouldReturnTheDefaultStrategyIfInvalidStrategyProvided(){
        Assert.assertEquals(levenshteinDistance,
                strategyFactory.getStrategyByStrategyType("INVALID"));
    }

    /**
     * test for getting levenshtein distance strategy when null is provided
     */
    @Test
    public void getLevenshteinDistanceStrategyShouldReturnDefaultStrategyWhenNullProvided(){
        Assert.assertEquals(levenshteinDistance, strategyFactory.getStrategyByStrategyType(null));
    }

    @Test
    public void getFileMetadataStrategyShouldReturnTheExpectedStrategy(){
        Assert.assertEquals(fileMetaData,
                strategyFactory.getStrategyByStrategyType("FILE_METADATA"));
    }

}