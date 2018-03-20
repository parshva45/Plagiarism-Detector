package edu.northeastern.cs5500.strategies;

import org.springframework.stereotype.Component;

/**
 * @author Praveen Singh
 */
@Component
public interface SimilarityStrategy {

    public double calculateSimilarity(String file1, String file2);

}
