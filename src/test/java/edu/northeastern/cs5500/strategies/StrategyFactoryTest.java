package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.strategies.implementations.LevenshteinDistance;
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
    StrategyFactory strategyFactory;

    @Test
    public void getStrategyShouldReturnTheExpectedStrategy(){
        Assert.assertEquals(levenshteinDistance,
                strategyFactory.getStrategyByStrategyType("LEVENSHTEIN_DISTANCE"));
    }

    @Test
    public void getStrategyShouldReturnTheDefaultStrategyIfInvalidStrategyProvided(){
        Assert.assertEquals(levenshteinDistance,
                strategyFactory.getStrategyByStrategyType("INVALID"));
    }

    @Test
    public void getStrategyShouldReturnDefaultStrategyWhenNullProvided(){
        Assert.assertEquals(levenshteinDistance, strategyFactory.getStrategyByStrategyType(null));
    }

}