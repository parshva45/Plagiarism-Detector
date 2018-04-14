package edu.northeastern.cs5500.strategies;

import org.springframework.stereotype.Component;

/**
 * @author Praveen Singh
 */
@Component
public interface SimilarityStrategy {

    /**
     * Method to calculate similarity measure between two submission files
     * submitted by two students
     * @param file1 String
     * @param file2 String
     * @return similarity measure between file1 and file2 double
     */
    double calculateSimilarity(String file1, String file2);

    /**
     *
     * @return array of similar lines
     */
    int[][] getsimilarLineNos(String file1, String file2);

}
