package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.strategies.implementations.FileMetaData;
import edu.northeastern.cs5500.strategies.implementations.LCS;
import edu.northeastern.cs5500.strategies.implementations.LevenshteinDistance;
import edu.northeastern.cs5500.strategies.implementations.WeightedScore;
import edu.northeastern.cs5500.strategies.implementations.ast.lcs.LongestCommonSubSequence;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.AstTreeEditDistance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Praveen Singh, namratabilurkar
 */
@Component
public class StrategyFactory {
    private static final Logger LOGGER = LogManager.getLogger(StrategyFactory.class);

    private final LevenshteinDistance levenshteinDistance;
    private final LCS lcs;
    private final LongestCommonSubSequence longestCommonSubSequence;
    private final FileMetaData fileMetaData;
    private final WeightedScore weightedScore;
    private final AstTreeEditDistance astTreeEditDistance;

    private static final String LOG_MSG = "Using given Strategy {}";

    /**
     * Method to instantiate Strategy factory
     * @param levenshteinDistance LevenshteinDistance
     * @param lcs LCS
     * @param astTreeEditDistance AST Tree edit distance
     * @param fileMetaData is the file meta data
     * @param longestCommonSubSequence is the AST LCS
     * @param weightedScore is the overall weighted score
     */
    @Autowired
    public StrategyFactory(LevenshteinDistance levenshteinDistance,
                           LCS lcs,
                           LongestCommonSubSequence longestCommonSubSequence,
                           FileMetaData fileMetaData, WeightedScore weightedScore,
                           AstTreeEditDistance astTreeEditDistance) {
        this.levenshteinDistance = levenshteinDistance;
        this.lcs = lcs;
        this.longestCommonSubSequence = longestCommonSubSequence;
        this.fileMetaData = fileMetaData;
        this.weightedScore = weightedScore;
        this.astTreeEditDistance = astTreeEditDistance;
    }

    /**
     * Given a string, method to return corresponding strategy
     * @param strategy String
     * @return corresponding strategy SimilarityStrategy
     */
    public SimilarityStrategy getStrategyByStrategyType(String strategy){
        if(StrategyTypes.LEVENSHTEIN_DISTANCE.toString().equals(strategy)) {
            LOGGER.info(LOG_MSG, StrategyTypes.LEVENSHTEIN_DISTANCE.toString());
            return levenshteinDistance;
        } else if(StrategyTypes.LCS.toString().equals(strategy)) {
            LOGGER.info(LOG_MSG, StrategyTypes.LCS.toString());
            return lcs;
        } else if (StrategyTypes.AST_LCS.toString().equals(strategy)) {
            LOGGER.info(LOG_MSG, StrategyTypes.AST_LCS.toString());
            return longestCommonSubSequence;
        } else if(StrategyTypes.FILE_METADATA.toString().equals(strategy)){
            LOGGER.info(LOG_MSG, StrategyTypes.FILE_METADATA.toString());
            return fileMetaData;
        } else if(StrategyTypes.WEIGHTED_SCORE.toString().equals(strategy)){
            LOGGER.info(LOG_MSG, StrategyTypes.WEIGHTED_SCORE.toString());
            return weightedScore;
        } else if(StrategyTypes.AST_TREE_EDIT_DISTANCE.toString().equals(strategy)){
            LOGGER.info(LOG_MSG, StrategyTypes.AST_TREE_EDIT_DISTANCE.toString());
            return astTreeEditDistance;
        }
        LOGGER.info(LOG_MSG, StrategyTypes.LEVENSHTEIN_DISTANCE.toString());
        return levenshteinDistance;
    }
}
