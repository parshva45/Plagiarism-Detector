package edu.northeastern.cs5500.strategies;

import org.junit.Assert;
import org.junit.Test;

import static edu.northeastern.cs5500.strategies.StrategyTypes.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Praveen Singh, namratabilurkar
 */
public class StrategyTypesTest {

    /**
     * tests for validating strategy enums
     */
    @Test
    public void validateEnums(){
        assertEquals("LEVENSHTEIN_DISTANCE", LEVENSHTEIN_DISTANCE.toString());
        assertEquals("LCS", LCS.toString());
        assertEquals("FILE_METADATA", FILE_METADATA.toString());
        assertEquals("AST_LCS", AST_LCS.toString());
        assertEquals("AST_TREE_EDIT_DISTANCE", AST_TREE_EDIT_DISTANCE.toString());
        assertEquals("WEIGHTED_SCORE", WEIGHTED_SCORE.toString());

        assertEquals(LEVENSHTEIN_DISTANCE, StrategyTypes.valueOf("LEVENSHTEIN_DISTANCE"));
        assertEquals(LCS, StrategyTypes.valueOf("LCS"));
        assertEquals(FILE_METADATA, StrategyTypes.valueOf("FILE_METADATA"));
        assertEquals(AST_LCS, StrategyTypes.valueOf("AST_LCS"));
        assertEquals(AST_TREE_EDIT_DISTANCE, StrategyTypes.valueOf("AST_TREE_EDIT_DISTANCE"));
        assertEquals(WEIGHTED_SCORE, StrategyTypes.valueOf("WEIGHTED_SCORE"));

        StrategyTypes[] list = {LEVENSHTEIN_DISTANCE, LCS, FILE_METADATA,
                AST_LCS, AST_TREE_EDIT_DISTANCE, WEIGHTED_SCORE};

        Assert.assertArrayEquals(list, StrategyTypes.values());
    }

}