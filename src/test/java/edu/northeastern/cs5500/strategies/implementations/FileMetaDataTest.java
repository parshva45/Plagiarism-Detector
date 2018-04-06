package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.Cs5500PlagiarismDetectorTeam207ApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author Praveen Singh
 */
public class FileMetaDataTest extends Cs5500PlagiarismDetectorTeam207ApplicationTests {

    @Autowired
    private FileMetaData fileMetaData;

    private String file1;
    private String file2;

    /**
     * setup submission files
     */
    @Before
    public void setUp() {
        file1 = getFilePath("submission1.py");
        file2 = getFilePath("submission2.py");
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
        double similarityMeasure = fileMetaData.calculateSimilarity(file1, file2);
        assertEquals(100.0, similarityMeasure, 0.01);
    }

}