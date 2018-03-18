package edu.northeastern.cs5500.strategies.levenshtein;

import java.util.Arrays;

public class LevenshteinDistance {
	
	private String compOne;
    private String compTwo;

    public LevenshteinDistance(String s1, String s2) {
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
                    matrix[i][j] = min(matrix[i - 1][j - 1] + costOfSubstitution(compOne.charAt(i - 1), compTwo.charAt(j - 1)), 
                    matrix[i - 1][j] + 1,
                    matrix[i][j - 1] + 1);
                }
            }
        }
     
        return matrix[compOne.length()][compTwo.length()];
    }
 
    private int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
 
    private int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
    
    public String longer(String s1, String s2) {
    	return s1.length() >= s2.length() ? s1 : s2;
    }

}
