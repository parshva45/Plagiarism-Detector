package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LCS implements SimilarityStrategy {

    private final PythonToStringParser pythonToStringParser;

    /**
     * Method to instantiate LCS
     * @param pythonToStringParser PythonToStringParser
     */
    @Autowired
    public LCS(PythonToStringParser pythonToStringParser) {
        this.pythonToStringParser = pythonToStringParser;
    }

    /* (non-Javadoc)
     * @see edu.northeastern.cs5500.strategies.SimilarityStrategy#calculateSimilarity(java.lang.String, java.lang.String)
     * Method to calculate similarity measure between file1 and file2 using LCS strategy
     * @param file1 String
     * @param file2 String
     * @return similarity measure between file1 and file2 using LCS strategy double
     */
    @Override
    public double calculateSimilarity(String file1, String file2){
    	
        String fileContentFile1 = pythonToStringParser.readFile(file1).trim();
        String fileContentFile2 = pythonToStringParser.readFile(file2).trim();
        
        int m = fileContentFile1.length();
    	int n = fileContentFile2.length();
    	int[][] L = new int[m+1][n+1];

        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (fileContentFile1.charAt(i-1) == fileContentFile2.charAt(j-1))
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }
  
        return L[m][n]/(double)longerLength(fileContentFile1,fileContentFile2)*100;
    }
    
    /**
     * Method to return longer string
     * @param s1 String
     * @param s2 String
     * @return length of the longer string among s1 and s2 String
     */
    private int longerLength(String s1, String s2) {
    	return s1.length() >= s2.length() ? s1.length() : s2.length();
    }

}
