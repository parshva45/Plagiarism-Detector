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
            int distance = getDistance(fileContentFile1, fileContentFile2);
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
                    int distance = getDistance(s1.toString(), s2.toString());
                    overallSimilaritySum += distance/(double)longerStringLength(s1.toString(), s2.toString())*100;
                }
            }
            return overallSimilaritySum/(firstSubmissionFiles.size()*secondSubmissionFiles.size());
        }
        else
        	throw new IllegalArgumentException();
    }

    /**
     * Method to calculate distance between file1 and file2 using LCS strategy (can be .py or .zip files)
     * @param s1 String
     * @param s2 String
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
     * Method to compute line numbers of similar lines between file1 and file2 using LCS strategy (can be .py or .zip files)
     * @param file1 String
     * @param file2 String
     * @return 2D int matrix
     */
    @Override
    public int[][] getsimilarLineNos(String file1, String file2) {
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
            return new int[0][0];
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Method to compute line numbers of similar lines between file1 and file2 using LCS strategy (can be .py or .zip files)
     * @param s1 String array with file1 lines
     * @param s2 String array with file2 lines
     * @return 2D int matrix
     */
    int[][] calculateLineNumbers(String[] s1, String[] s2) {
        Map<Integer, Integer> selectedLinesMap;
        int[][] similarLineNos;
        selectedLinesMap = new HashMap<>();
        int k=0;
        similarLineNos = new int[2][longerLength(s1,s2)];
        for(int i=0; i<2; i++) {
            for (int j = 0; j < similarLineNos[i].length; j++) {
                similarLineNos[i][j] = -1;
            }
        }
        for(String i :s1)
        {
            int max = 0;
            int l=0;
            similarLineNos[1][k] = -1;
            i = i.trim();
            i = i.replaceAll("\\s+","");
            for (String j : s2)
            {
                j = j.trim();
                j = j.replaceAll("\\s+","");
                String result = lcs(i.toLowerCase(), j.toLowerCase());
                if(!selectedLinesMap.containsKey(l) && result.length() > max && result.length() >= i.length() * 0.95 && result.length() >= j.length() *0.95)
                {
                    similarLineNos[1][k] = l;
                    max = result.length();
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
            int max = 0;
            int l=0;
            similarLineNos[0][k] = -1;
            i = i.trim();
            i = i.replaceAll("\\s+","");
            for (String j : s1)
            {
                j = j.trim();
                j = j.replaceAll("\\s+","");
                String result = lcs(i.toLowerCase(), j.toLowerCase());
                if(!selectedLinesMap.containsKey(l) && result.length() > max && result.length() >= i.length() * 0.95 && result.length() >= j.length() *0.95)
                {
                    similarLineNos[0][k] = l;
                    max = result.length();
                }
                selectedLinesMap.put(similarLineNos[0][k], 1);
                l++;
            }
            k++;
        }
        return similarLineNos;
    }

    /**
     * LCS algorithm
     * @param str1 String from first file that needs to be compared
     * @param str2 String from second file that needs to be compared
     * @return
     */
    public String lcs(String str1, String str2)
    {
        int l1 = str1.length();
        int l2 = str2.length();

        int[][] arr = new int[l1 + 1][l2 + 1];

        for (int i = l1 - 1; i >= 0; i--)
        {
            for (int j = l2 - 1; j >= 0; j--)
            {
                if (str1.charAt(i) == str2.charAt(j))
                    arr[i][j] = arr[i + 1][j + 1] + 1;
                else
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
            }
        }

        int i = 0;
        int j = 0;
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
        return sb.toString();
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
