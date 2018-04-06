package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.strategies.implementations.FileMetaData;
import edu.northeastern.cs5500.strategies.implementations.LCS;
import edu.northeastern.cs5500.strategies.implementations.LevenshteinDistance;
import edu.northeastern.cs5500.strategies.implementations.ast.lcs.LongestCommonSubSequence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Praveen Singh
 */
@Component
public class StrategyFactory {
    private static final Logger LOGGER = LogManager.getLogger(StrategyFactory.class);

    private final LevenshteinDistance levenshteinDistance;
    private final LCS lcs;
    private final LongestCommonSubSequence longestCommonSubSequence;
    private final FileMetaData fileMetaData;

    /**
     * Method to instantiate Strategy factory
     * @param levenshteinDistance LevenshteinDistance
     * @param lcs LCS
     */
    @Autowired
    public StrategyFactory(LevenshteinDistance levenshteinDistance,
                           LCS lcs,
                           LongestCommonSubSequence longestCommonSubSequence,
                           FileMetaData fileMetaData) {
        this.levenshteinDistance = levenshteinDistance;
        this.lcs = lcs;
        this.longestCommonSubSequence = longestCommonSubSequence;
        this.fileMetaData = fileMetaData;
    }

    /**
     * Given a string, method to return corresponding strategy
     * @param strategy String
     * @return corresponding strategy SimilarityStrategy
     */
    public SimilarityStrategy getStrategyByStrategyType(String strategy){
        if(StrategyTypes.LEVENSHTEIN_DISTANCE.toString().equals(strategy)) {
            LOGGER.info("Using given Strategy {}", StrategyTypes.LEVENSHTEIN_DISTANCE.toString());
            return levenshteinDistance;
        } else if(StrategyTypes.LCS.toString().equals(strategy)) {
            LOGGER.info("Using given Strategy {}", StrategyTypes.LCS.toString());
            return lcs;
        } else if (StrategyTypes.AST_LCS.toString().equals(strategy)) {
            LOGGER.info("Using given Strategy {}", StrategyTypes.AST_LCS.toString());
            return longestCommonSubSequence;
        } else if(StrategyTypes.FILE_METADATA.toString().equals(strategy)){
            LOGGER.info("Using given Strategy {}", StrategyTypes.FILE_METADATA.toString());
            return fileMetaData;
        }
        LOGGER.info("Using default Strategy {}", StrategyTypes.LEVENSHTEIN_DISTANCE.toString());
        return levenshteinDistance;
    }
}
