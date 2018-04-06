package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.implementations.ast.lcs.LongestCommonSubSequence;
import edu.northeastern.cs5500.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of the Similarity Strategy interface for giving weighted
 * similarity by the utilizing all the methods
 */
@Component
public class WeightedScore implements SimilarityStrategy {

    private final LCS lcs;
    private final LevenshteinDistance levenshteinDistance;
    private final FileMetaData fileMetaData;
    private final LongestCommonSubSequence longestCommonSubSequence;

    @Autowired
    public WeightedScore(LCS lcs, LevenshteinDistance levenshteinDistance,
                         FileMetaData fileMetaData,
                         LongestCommonSubSequence longestCommonSubSequence) {
        this.lcs = lcs;
        this.levenshteinDistance = levenshteinDistance;
        this.fileMetaData = fileMetaData;
        this.longestCommonSubSequence = longestCommonSubSequence;
    }


    @Override
    public double calculateSimilarity(String file1, String file2) {
        double lcsScore = lcs.calculateSimilarity(file1, file2);
        double levScore = levenshteinDistance.calculateSimilarity(file1, file2);
        double fileMetaDataScore = fileMetaData.calculateSimilarity(file1, file2);
        double longestCommenSubScore = longestCommonSubSequence.calculateSimilarity(file1, file2);
        return (Constants.LCS_WEIGHT * lcsScore) + (Constants.LEV_WEIGHT * levScore)
                + (Constants.METADATA_WEIGHT * fileMetaDataScore)
                + (Constants.AST_LCS_WEIGHT * longestCommenSubScore);
    }
}
