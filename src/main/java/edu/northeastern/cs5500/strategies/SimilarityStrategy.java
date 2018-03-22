package edu.northeastern.cs5500.strategies;

import org.springframework.stereotype.Component;

/**
 * @author Praveen Singh
 */
@Component
public interface SimilarityStrategy {

    /**
     * @param file1
     * @param file2
     * @return similarity measure between file1 and file2
     */
    double calculateSimilarity(String file1, String file2);

}
