package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.Cs5500PlagiarismDetectorTeam207ApplicationTests;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.AstTreeEditDistance;
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
public class AstTreeEditDistanceTest extends Cs5500PlagiarismDetectorTeam207ApplicationTests {

    @Autowired
    AstTreeEditDistance astTreeEditDistance;

    private String file1;
    private String file2;
    private String file3;
    private String file4;
    private String simple;

    /**
     * setup submission files
     */
    @Before
    public void setUp() {
        file1 = getFilePath("submission1.py");
        file2 = getFilePath("submission2.py");
        file3 = getFilePath("submission3.py");
        file4 = getFilePath("submission4.py");
        simple = getFilePath("simple.py");
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
     * test similarity measure between submission1.py and simple.py
     */
    @Test
    public void compares1andSimpleTest() {
        double similarityMeasure = astTreeEditDistance.calculateSimilarity(simple, file1);
        assertEquals(90.095, similarityMeasure, 0.01);
    }
    /**
     * test similarity measure between submission1.py and submission2.py
     */
    @Test
    public void compares1s2Test() {
        double similarityMeasure = astTreeEditDistance.calculateSimilarity(file1, file2);
        assertEquals(90.735, similarityMeasure, 0.01);
    }

    @After
    public void runAfterTestMethod(){

    }

    /**
     * test similarity measure between submission1.py and submission3.py
     */
    @Test
    public void compares1s3Test() {
        double similarityMeasure = astTreeEditDistance.calculateSimilarity(file1, file3);
        assertEquals(89.403, similarityMeasure, 0.01);
    }

    /**
     * test similarity measure between submission1.py and submission4.py
     */
    @Test
    public void compares1s4Test() {
        double similarityMeasure = astTreeEditDistance.calculateSimilarity(file1, file4);
        assertEquals(89.758, similarityMeasure, 0.01);
    }

    /**
     * test similarity measure between submission2.py and submission3.py
     */
    @Test
    public void compares2s3Test() {
        double similarityMeasure = astTreeEditDistance.calculateSimilarity(file2, file3);
        assertEquals(90.395, similarityMeasure, 0.01);
    }

    /**
     * test similarity measure between submission2.py and submission4.py
     */
    @Test
    public void compares2s4Test() {
        double similarityMeasure = astTreeEditDistance.calculateSimilarity(file2, file4);
        assertEquals(90.736, similarityMeasure, 0.01);
    }


    /**
     * test similarity measure by getting submission files
     */
    @Test
    public void calculateSimilarityShouldGiveCorrectResult(){
        double res = astTreeEditDistance.calculateSimilarity(
                getFilePath("submission3.py"), getFilePath("submission4.py"));
        assertEquals(89.605, res, 0.01);
    }

}
