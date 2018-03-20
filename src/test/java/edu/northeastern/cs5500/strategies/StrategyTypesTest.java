package edu.northeastern.cs5500.strategies;

import org.junit.Assert;
import org.junit.Test;

import static edu.northeastern.cs5500.strategies.StrategyTypes.FILE_METADATA;
import static edu.northeastern.cs5500.strategies.StrategyTypes.LEVENSHTEIN_DISTANCE;
import static org.junit.Assert.assertEquals;

/**
 * @author Praveen Singh
 */
public class StrategyTypesTest {

    @Test
    public void validateEnums(){
        assertEquals("LEVENSHTEIN_DISTANCE", LEVENSHTEIN_DISTANCE.toString());
        assertEquals("FILE_METADATA", FILE_METADATA.toString());

        assertEquals(LEVENSHTEIN_DISTANCE, StrategyTypes.valueOf("LEVENSHTEIN_DISTANCE"));
        assertEquals(FILE_METADATA, StrategyTypes.valueOf("FILE_METADATA"));

        StrategyTypes[] list = {LEVENSHTEIN_DISTANCE, FILE_METADATA};

        Assert.assertArrayEquals(list, StrategyTypes.values());
    }

}