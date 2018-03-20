package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.strategies.implementations.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Praveen Singh
 */
@Component
public class StrategyFactory {

    private final LevenshteinDistance levenshteinDistance;

    @Autowired
    public StrategyFactory(LevenshteinDistance levenshteinDistance) {
        this.levenshteinDistance = levenshteinDistance;
    }

    public SimilarityStrategy getStrategyByStrategyType(String strategy){
        if(strategy.equals(StrategyTypes.LEVENSHTEIN_DISTANCE.toString())){
            return levenshteinDistance;
        }
        return levenshteinDistance;
    }
}
