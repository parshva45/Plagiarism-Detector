package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.implementations.ast.lcs.LongestCommonSubSequence;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.AstTreeEditDistance;
import edu.northeastern.cs5500.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of the Similarity Strategy interface for giving weighted
 * similarity by the utilizing all the methods
 * @author namratabilurkar
 */
@Component
public class WeightedScore implements SimilarityStrategy {

    private final LCS lcs;
    private final LevenshteinDistance levenshteinDistance;
    private final LongestCommonSubSequence longestCommonSubSequence;
    private final AstTreeEditDistance astTreeEditDistance;

    /**
     * Parameterized constructor
     * @param lcs is the object to LCS strategy
     * @param levenshteinDistance  is the object to Levenshtein strategy
     * @param longestCommonSubSequence is the object to AST LCS strategy
     * @param astTreeEditDistance is the object to AST Tree edit distance strategy
     */
    @Autowired
    public WeightedScore(LCS lcs, LevenshteinDistance levenshteinDistance,
                         LongestCommonSubSequence longestCommonSubSequence,
                         AstTreeEditDistance astTreeEditDistance) {
        this.lcs = lcs;
        this.levenshteinDistance = levenshteinDistance;
        this.longestCommonSubSequence = longestCommonSubSequence;
        this.astTreeEditDistance = astTreeEditDistance;
    }


    /**
     * Calucaltes the weighted score
     * @param file1 String is the path to file1
     * @param file2 String is the path to file2
     * @return the weighted similarity score using all the comparison strategies
     */
    @Override
    public double calculateSimilarity(String file1, String file2) {

        double lcsScore = lcs.calculateSimilarity(file1, file2);
        double levScore = levenshteinDistance.calculateSimilarity(file1, file2);
        double longestCommonSubScore = longestCommonSubSequence.calculateSimilarity(file1, file2);
        double treeEditDistScore = astTreeEditDistance.calculateSimilarity(file1, file2);

        return (Constants.LCS_WEIGHT * lcsScore) + (Constants.LEV_WEIGHT * levScore)
                + (Constants.AST_LCS_WEIGHT * longestCommonSubScore
        + (Constants.AST_TREE_EDIT_WEIGHT * treeEditDistScore));
    }

    @Override
    public Integer[][] getsimilarLineNos(String file1, String file2) {
        return new Integer[0][];
    }

}
