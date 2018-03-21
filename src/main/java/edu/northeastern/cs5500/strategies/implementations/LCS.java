package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LCS implements SimilarityStrategy {

    private final PythonToStringParser pythonToStringParser;

    @Autowired
    public LCS(PythonToStringParser pythonToStringParser) {
        this.pythonToStringParser = pythonToStringParser;
    }

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
    
    private int longerLength(String s1, String s2) {
    	return s1.length() >= s2.length() ? s1.length() : s2.length();
    }

}
