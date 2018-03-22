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
     * @param levenshteinDistance
     * @param lcs
     */
    @Autowired
    public StrategyFactory(LevenshteinDistance levenshteinDistance, LCS lcs) {
        this.levenshteinDistance = levenshteinDistance;
        this.lcs = lcs;
    }

    /**
     * @param strategy
     * @return corresponding strategy
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
