package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeightedScore implements SimilarityStrategy {

    @Autowired
    LCS lcs;

    @Autowired
    LevenshteinDistance levenshteinDistance;

    @Autowired
    FileMetaData fileMetaData;


    @Override
    public double calculateSimilarity(String file1, String file2) {

        double weightedScore;

        double lcsScore = lcs.calculateSimilarity(file1, file2);
        double levScore = levenshteinDistance.calculateSimilarity(file1, file2);
        double fileMetaDataScore = fileMetaData.calculateSimilarity(file1, file2);

        weightedScore = Constants.LCSWEIGHT * lcsScore + Constants.LEVWEIGHT * levScore +
                Constants.METADATAWEIGHT * fileMetaDataScore;

        return weightedScore;
    }
}
