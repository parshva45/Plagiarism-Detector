package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.Cs5500PlagiarismDetectorTeam207ApplicationTests;
import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.implementations.ast.lcs.LongestCommonSubSequence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * @author namratabilurkar
 */

public class AstLCSTest extends Cs5500PlagiarismDetectorTeam207ApplicationTests {

    @Autowired
    PythonToStringParser pythonToStringParser;

    @Autowired
    LongestCommonSubSequence longestCommonSubSequence;

    private String file1;
    private String file2;
    private String file3;
    private String file4;

    /**
     * setup submission files
     */
    @Before
    public void setUp() {
        file1 = getFilePath("submission1.py");
        file2 = getFilePath("submission2.py");
        file3 = getFilePath("submission3.py");
        file4 = getFilePath("submission4.py");
    }

    /**
     * @param fileName of file to be get
     * @return absolute path of file
     */
    public String getFilePath(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName))
                .getFile());
        return file.getAbsolutePath();
    }

    /**
     * test similarity measure between submission1.py and submission2.py
     */
    @Test
    public void compares1s2Test() {
        double similarityMeasure = longestCommonSubSequence.calculateSimilarity(file1, file2);
        assertEquals(35.249, similarityMeasure, 0.01);
    }

    @After
    public void runAfterTestMethod(){

    }

    /**
     * test similarity measure between submission1.py and submission3.py
     */
    @Test
    public void compares1s3Test() {
        double similarityMeasure = longestCommonSubSequence.calculateSimilarity(file1, file3);
        assertEquals(44.951, similarityMeasure, 0.01);
    }

    /**
     * test similarity measure between submission1.py and submission4.py
     */
    @Test
    public void compares1s4Test() {
        double similarityMeasure = longestCommonSubSequence.calculateSimilarity(file1, file4);
        assertEquals(19.686, similarityMeasure, 0.01);
    }

    /**
     * test similarity measure between submission2.py and submission3.py
     */
    @Test
    public void compares2s3Test() {
        double similarityMeasure = longestCommonSubSequence.calculateSimilarity(file2, file3);
        assertEquals(59.928, similarityMeasure, 0.01);
    }

    /**
     * test similarity measure between submission2.py and submission4.py
     */
    @Test
    public void compares2s4Test() {
        double similarityMeasure = longestCommonSubSequence.calculateSimilarity(file2, file4);
        assertEquals(30.849, similarityMeasure, 0.01);
    }


    /**
     * test similarity measure by getting submission files
     */
    @Test
    public void calculateSimilarityShouldGiveCorrectResult(){
        double res = longestCommonSubSequence.calculateSimilarity(
                getFilePath("submission3.py"), getFilePath("submission4.py"));
        assertEquals(22.802, res, 0.01);
    }

}

