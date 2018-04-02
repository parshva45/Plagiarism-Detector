package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.Cs5500PlagiarismDetectorTeam207ApplicationTests;
import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.implementations.LCS;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LCSTest extends Cs5500PlagiarismDetectorTeam207ApplicationTests{
	
	@Autowired
	PythonToStringParser pythonToStringParser;
	
	@Autowired
	LCS lcs;

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
    	double similarityMeasure = lcs.calculateSimilarity(file1, file2);
    	assertEquals(21.96, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission1.py and submission3.py
	 */
	@Test
	public void compares1s3Test() {
		double similarityMeasure = lcs.calculateSimilarity(file1, file3);
    	assertEquals(43.25, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission1.py and submission4.py
	 */
	@Test
	public void compares1s4Test() {
		double similarityMeasure = lcs.calculateSimilarity(file1, file4);
    	assertEquals(14.67, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission2.py and submission3.py
	 */
	@Test
	public void compares2s3Test() {
		double similarityMeasure = lcs.calculateSimilarity(file2, file3);
    	assertEquals(20.82, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission2.py and submission4.py
	 */
	@Test
	public void compares2s4Test() {
		double similarityMeasure = lcs.calculateSimilarity(file2, file4);
    	assertEquals(28.34, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission3.py and submission4.py
	 */
	@Test
	public void compares3s4Test() {
    	double similarityMeasure = lcs.calculateSimilarity(file3, file4);
    	assertEquals(12.96, similarityMeasure, 0.01);
	}

	/**
	 * test for non-existent file read
	 */
	@Test
	public void exceptionTest() {
		pythonToStringParser.readFile("submission5.py");
	}

	/**
	 * test similarity measure by getting submission files
	 */
	@Test
	public void calculateSimilarityShouldGiveCorrectResult(){
		double res = lcs.calculateSimilarity(
				getFilePath("submission3.py"), getFilePath("submission4.py"));
		assertEquals(12.96, res, 0.01);
	}

	/**
	 * test similarity measure between submission1.zip files and submission2.zip files
	 */
    @Test
    public void compares1s2ZipTest() {
        double res = lcs.calculateSimilarity(
                getFilePath("submission1.zip"), getFilePath("submission2.zip"));
        assertEquals(23.67, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission1.zip files and submission3.zip files
	 */
    @Test
    public void compares1s3ZipTest() {
        double res = lcs.calculateSimilarity(
                getFilePath("submission1.zip"), getFilePath("submission3.zip"));
        assertEquals(35.09, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission1.zip files and submission4.zip files
	 */
    @Test
    public void compares1s4ZipTest() {
        double res = lcs.calculateSimilarity(
                getFilePath("submission1.zip"), getFilePath("submission4.zip"));
        assertEquals(27.48, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission2.zip files and submission3.zip files
	 */
    @Test
    public void compares2s3ZipTest() {
        double res = lcs.calculateSimilarity(
                getFilePath("submission2.zip"), getFilePath("submission3.zip"));
        assertEquals(17.47, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission2.zip files and submission4.zip files
	 */
    @Test
    public void compares2s4ZipTest() {
        double res = lcs.calculateSimilarity(
                getFilePath("submission2.zip"), getFilePath("submission4.zip"));
        assertEquals(23.17, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission3.zip files and submission4.zip files
	 */
    @Test
    public void compares3s4ZipTest() {
        double res = lcs.calculateSimilarity(
                getFilePath("submission3.zip"), getFilePath("submission4.zip"));
        assertEquals(25.06, res, 0.01);
    }
    
    /**
	 * test for non-existent zip file read
	 */
	@Test
	public void exceptionZipTest() {
		pythonToStringParser.parseFiles("submission5.zip");
	}
	
	/**
	 * test for one .zip and another .py submission
	 */
	@Test(expected = IllegalArgumentException.class)
    public void comparesOneZipOnePythonTest() {
        lcs.calculateSimilarity(
                getFilePath("submission3.zip"), getFilePath("submission4.py"));
        fail();
    }
	
	/**
	 * test for one .py and another .zip submission
	 */
	@Test(expected = IllegalArgumentException.class)
    public void comparesOnePythonOneZipTest() {
        lcs.calculateSimilarity(
                getFilePath("submission3.py"), getFilePath("submission4.zip"));
        fail();
    }

}
