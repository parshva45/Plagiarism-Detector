package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * Method to calculate similarity measure between file1 and file2 using LCS strategy
     * @param file1 String
     * @param file2 String
     * @return similarity measure between file1 and file2 using LCS strategy double
     */
    @Override
    public double calculateSimilarity(String file1, String file2){
    	String ext1 = FilenameUtils.getExtension(file1);
        String ext2 = FilenameUtils.getExtension(file2);
        /**
         * If single file comparison between two .py files is expected 
         */
        if(ext1.equals("py") && ext2.equals("py")){
	        String fileContentFile1 = pythonToStringParser.readFile(file1).trim();
	        String fileContentFile2 = pythonToStringParser.readFile(file2).trim();
	        int distance = getDistance(fileContentFile1, fileContentFile2);
	        return distance/(double)longerLength(fileContentFile1,fileContentFile2)*100;
        }
        /**
         * If multiple file comparisons across two .zip files is expected 
         */
        else if(ext1.equals("zip") && ext2.equals("zip")){
            List<StringBuilder> firstSubmissionFiles = pythonToStringParser.parseFiles(file1);
            List<StringBuilder> secondSubmissionFiles = pythonToStringParser.parseFiles(file2);
            double overallSimilaritySum = 0;
            for (StringBuilder s1 : firstSubmissionFiles) {
                for (StringBuilder s2 : secondSubmissionFiles) {
                    int distance = getDistance(s1.toString(), s2.toString());
                    overallSimilaritySum += distance/(double)longerLength(s1.toString(), s2.toString())*100;
                }
            }
            return overallSimilaritySum/(firstSubmissionFiles.size()*secondSubmissionFiles.size());
        }
        else
        	throw new IllegalArgumentException();
    }
    
    /**
     * Method to calculate distance between file1 and file2 using LCS strategy (can be .py or .zip files)
     * @param file1 String
     * @param file2 String
     * @return distance between file1 and file2 using LCS strategy int
     */
    int getDistance(String s1, String s2) {
    	int m = s1.length();
    	int n = s2.length();
    	int[][] l = new int[m+1][n+1];

        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    l[i][j] = 0;
                else if (s1.charAt(i-1) == s2.charAt(j-1))
                    l[i][j] = l[i-1][j-1] + 1;
                else
                    l[i][j] = Math.max(l[i-1][j], l[i][j-1]);
            }
        }
        return l[m][n];
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
