package edu.northeastern.cs5500.strategies.levenshtein;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LevenshteinDistance {
    private static final Logger LOGGER = Logger.getLogger(LevenshteinDistance.class.getName());
    private String compOne;
    private String compTwo;

    LevenshteinDistance(String s1, String s2) {
        compOne = s1;
        compTwo = s2;
    }

    public int getDistance() {

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
 
    private int costOfSubstitution(char first, char second) {
        return first == second ? 0 : 1;
    }
 
    private int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
    
    public String longer(String s1, String s2) {
    	return s1.length() >= s2.length() ? s1 : s2;
    }

    /**
     * Method to read file and return content as a string for comparison.
     * @param filePath Path of File in string.
     * @return Returns the file content as a String.
     */
    public String readFile(String filePath){
        StringBuilder contentBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() > 0)
                    contentBuilder.append(line).append("\n");
            }
        } catch (IOException ex) {
            LOGGER.log(Level.ALL, "Exception while reading file {0}", ex.getMessage());
        }
    	return contentBuilder.toString();
    }

}
