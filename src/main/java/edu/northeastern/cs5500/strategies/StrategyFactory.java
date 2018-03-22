package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.strategies.implementations.LevenshteinDistance;
import edu.northeastern.cs5500.strategies.implementations.LCS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Praveen Singh
 */
@Component
public class StrategyFactory {

    private final LevenshteinDistance levenshteinDistance;
    private final LCS lcs;

    /**
     * Method to instantiate Strategy factory
     * @param levenshteinDistance LevenshteinDistance
     * @param lcs LCS
     */
    @Autowired
    public StrategyFactory(LevenshteinDistance levenshteinDistance, LCS lcs) {
        this.levenshteinDistance = levenshteinDistance;
        this.lcs = lcs;
    }

    /**
     * Given a string, method to return corresponding strategy
     * @param strategy String
     * @return corresponding strategy SimilarityStrategy
     */
    public SimilarityStrategy getStrategyByStrategyType(String strategy){
        if(StrategyTypes.LEVENSHTEIN_DISTANCE.toString().equals(strategy)){
            return levenshteinDistance;
        }
        if(StrategyTypes.LCS.toString().equals(strategy)){
            return lcs;
        }
        return levenshteinDistance;
    }
}
