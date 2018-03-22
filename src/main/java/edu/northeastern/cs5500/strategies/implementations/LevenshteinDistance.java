package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LevenshteinDistance implements SimilarityStrategy {

    private final PythonToStringParser pythonToStringParser;

    /**
     * @param pythonToStringParser
     */
    @Autowired
    public LevenshteinDistance(PythonToStringParser pythonToStringParser) {
        this.pythonToStringParser = pythonToStringParser;
    }

    /**
     * @param compOne
     * @param compTwo
     * @return edit distance between strings compOne and compTwo using Levenshtein distance
     */
    public int getDistance(String compOne, String compTwo) {
        int[][] matrix = new int[compOne.length() + 1][compTwo.length() + 1];
        for (int i = 0; i <= compOne.length(); i++) {
            for (int j = 0; j <= compTwo.length(); j++) {
                if (i == 0) {
                    matrix[i][j] = j;
                }
                else if (j == 0) {
                    matrix[i][j] = i;
                }
                else {
                    matrix[i][j] = min(matrix[i - 1][j - 1]
                                    + costOfSubstitution(compOne.charAt(i - 1),
                            compTwo.charAt(j - 1)),
                    matrix[i - 1][j] + 1,
                    matrix[i][j - 1] + 1);
                }
            }
        }
     
        return matrix[compOne.length()][compTwo.length()];
    }
 
    /**
     * @param first
     * @param second
     * @return cost of substitution between chars first and second
     */
    private int costOfSubstitution(char first, char second) {
        return first == second ? 0 : 1;
    }
 
    /**
     * @param numbers
     * @return number with minimum value if exists, max integer otherwise
     */
    private int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
    
    /**
     * @param s1
     * @param s2
     * @return longer of the two strings s1 and s2
     */
    public String longer(String s1, String s2) {
    	return s1.length() >= s2.length() ? s1 : s2;
    }


    /* (non-Javadoc)
     * @see edu.northeastern.cs5500.strategies.SimilarityStrategy#calculateSimilarity(java.lang.String, java.lang.String)
     * similarity measure between file1 and file2 using Levenshtein distance strategy
     */
    @Override
    public double calculateSimilarity(String file1, String file2){
        String fileContentFile1 = pythonToStringParser.readFile(file1).trim();
        String fileContentFile2 = pythonToStringParser.readFile(file2).trim();
        int distance = getDistance(fileContentFile1, fileContentFile2);
        return (1-(double)distance / longer(fileContentFile1, fileContentFile2).length()) * 100;
    }

}
