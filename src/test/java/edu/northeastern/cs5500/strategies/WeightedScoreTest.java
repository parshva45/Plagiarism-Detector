package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.Cs5500PlagiarismDetectorTeam207ApplicationTests;
import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.implementations.WeightedScore;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class WeightedScoreTest extends Cs5500PlagiarismDetectorTeam207ApplicationTests {

    @Autowired
    WeightedScore weightedScore;

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
        double similarityMeasure = weightedScore.calculateSimilarity(file1, file2);
        assertEquals(42.671, similarityMeasure, 0.01);
    }

    /**
     * test similarity measure between submission1.py and submission3.py
     */
    @Test
    public void compares1s3Test() {
        double similarityMeasure = weightedScore.calculateSimilarity(file1, file3);
        assertEquals(63.718, similarityMeasure, 0.01);
    }

}