package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
            String fileContentFile1 = pythonToStringParser.readFile(file1);
            String fileContentFile2 = pythonToStringParser.readFile(file2);
            int distance = getMatrix(fileContentFile1, fileContentFile2)[fileContentFile1.length()][fileContentFile2.length()];
            return distance/(double)longerStringLength(fileContentFile1,fileContentFile2)*100;
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
                    int distance = getMatrix(s1.toString(), s2.toString())[s1.toString().length()][s2.toString().length()];
                    overallSimilaritySum += distance/(double)longerStringLength(s1.toString(), s2.toString())*100;
                }
            }
            return overallSimilaritySum/(firstSubmissionFiles.size()*secondSubmissionFiles.size());
        }
        else
        	throw new IllegalArgumentException();
    }

    /**
     * Method to calculate distance matrix between file1 and file2 using LCS strategy (can be .py or .zip files)
     * @param s1 String
     * @param s2 String
     * @return distance matrix between file1 and file2 using LCS strategy int
     */
    int[][] getMatrix(String s1, String s2) {
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
        return l;
    }
    
    /**
     * @param str1 String from first file that needs to be compared
     * @param str2 String from second file that needs to be compared
     * @return LCS string length of str1 and str2 int
     */
    private int lcs(String str1, String str2)
    {
        int l1 = str1.length();
        int l2 = str2.length();

        int i = 0;
        int j = 0;

        int[][] arr = getMatrix(str1, str2);

        StringBuilder sb = new StringBuilder();
        while (i < l1 && j < l2)
        {
            if (str1.charAt(i) == str2.charAt(j))
            {
                sb.append(str1.charAt(i));
                i++;
                j++;
            }
            else if (arr[i + 1][j] >= arr[i][j + 1])
                i++;
            else
                j++;
        }
        return sb.toString().length();
    }

    /**
     * Method to compute line numbers of similar lines between file1 and file2 using LCS strategy (can be .py or .zip files)
     * @param file1 String
     * @param file2 String
     * @return 2D Integer matrix
     */
    @Override
    public Integer[][] getSimilarLineNos(String file1, String file2) {
        String ext1 = FilenameUtils.getExtension(file1);
        String ext2 = FilenameUtils.getExtension(file2);
        /**
         * If single file comparison between two .py files is expected
         */
        if(ext1.equals("py") && ext2.equals("py")){
            String[] fileLines1 = pythonToStringParser.readFile(file1).split("\\r?\\n");
            String[] fileLines2 = pythonToStringParser.readFile(file2).split("\\r?\\n");
            return calculateLineNumbers(fileLines1, fileLines2);
        }
        /**
         * If multiple file comparisons across two .zip files is expected
         */
        else if(ext1.equals("zip") && ext2.equals("zip")){
            return new Integer[0][0];
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Method to compute line numbers of similar lines between file1 and file2 using LCS strategy (can be .py or .zip files)
     * @param s1 String array with file1 lines
     * @param s2 String array with file2 lines
     * @return 2D Integer matrix
     */
    private Integer[][] calculateLineNumbers(String[] s1, String[] s2) {
        Map<Integer, Integer> selectedLinesMap;
        Integer[][] similarLineNos;
        selectedLinesMap = new HashMap<>();
        int k=0;
        similarLineNos = new Integer[2][longerLength(s1,s2)];
        for(int i=0; i<2; i++) {
            for (int j = 0; j < similarLineNos[i].length; j++) {
                similarLineNos[i][j] = -1;
            }
        }
        for(String i :s1)
        {
            double max = 0;
            int l=0;
            i = i.trim();
            i = i.replaceAll("\\s+","");
            for (String j : s2)
            {
                j = j.trim();
                j = j.replaceAll("\\s+","");
                double result;
                if(longerStringLength(i, j) == 0)
                    result = 0;
                else
                    result = lcs(i.toLowerCase(), j.toLowerCase())/(double)longerStringLength(i, j);
                if(!selectedLinesMap.containsKey(l) && result > max && result >= 0.85)
                {
                    similarLineNos[1][k] = l;
                    max = result;
                }
                selectedLinesMap.put(similarLineNos[1][k], 1);
                l++;
            }
            k++;
        }

        selectedLinesMap = new HashMap<>();
        k=0;
        for(String i :s2)
        {
            double max = 0;
            int l=0;
            i = i.trim();
            i = i.replaceAll("\\s+","");
            for (String j : s1)
            {
                j = j.trim();
                j = j.replaceAll("\\s+","");
                double result;
                if(longerStringLength(i, j) == 0)
                    result = 0;
                else
                    result = lcs(i.toLowerCase(), j.toLowerCase())/(double)longerStringLength(i, j);
                if(!selectedLinesMap.containsKey(l) && result > max && result >= 0.85)
                {
                    similarLineNos[0][k] = l;
                    max = result;
                }
                selectedLinesMap.put(similarLineNos[0][k], 1);
                l++;
            }
            k++;
        }
        return similarLineNos;
    }

    /**
     * Method to return length of longer string array
     * @param s1 String array
     * @param s2 String array
     * @return length of the longer string array among s1 and s2 String arrays
     */
    private int longerLength(String[] s1, String[] s2){
        return s1.length >= s2.length ? s1.length : s2.length;
    }

    /**
     * Method to return length of longer string
     * @param s1 String
     * @param s2 String
     * @return length of the longer string among s1 and s2 Strings
     */
    private int longerStringLength(String s1, String s2) {
        return s1.length() >= s2.length() ? s1.length() : s2.length();
    }
}
