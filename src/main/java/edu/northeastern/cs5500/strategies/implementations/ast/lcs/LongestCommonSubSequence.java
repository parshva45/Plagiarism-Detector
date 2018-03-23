package edu.northeastern.cs5500.strategies.implementations.ast.lcs;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import org.springframework.stereotype.Component;

/**
 * @author namratabilurkar
 */

@Component
public class LongestCommonSubSequence implements SimilarityStrategy{

    public int[] lcsLength(String ast1, String ast2) {

        int lengthOfAst1 = ast1.length();
        int lengthOfAst2 = ast2.length();

        int[] lcsLengthAndBase = new int[2];

        int L[][] = new int[lengthOfAst1+1][lengthOfAst2+1];


        for (int i=0; i<=lengthOfAst1; i++) {
            for (int j=0; j<=lengthOfAst2; j++) {
                if (i==0 || j==0) {
                    L[i][j] = 0;
                } else if (ast1.charAt(i-1) == ast2.charAt(j-1)) {
                    L[i][j] = L[i-1][j-1] + 1;
                } else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
        }

        lcsLengthAndBase[0] = L[lengthOfAst1][lengthOfAst2];
        lcsLengthAndBase[1] = Math.max(lengthOfAst1, lengthOfAst2);

        return lcsLengthAndBase;
    }

    @Override
    public double calculateSimilarity(String file1, String file2) {
        int[] lcsValues = lcsLength(file1, file2);
        double simScore = (lcsValues[0]/lcsValues[1]) * 100;
        return simScore;
    }

}
