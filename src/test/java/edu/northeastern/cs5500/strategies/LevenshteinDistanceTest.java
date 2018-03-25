package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.Cs5500PlagiarismDetectorTeam207ApplicationTests;
import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.implementations.LevenshteinDistance;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class LevenshteinDistanceTest extends Cs5500PlagiarismDetectorTeam207ApplicationTests{
	
	@Autowired
	PythonToStringParser pythonToStringParser;
	
	@Autowired
	LevenshteinDistance levenshteinDistanceObj;

	private String s1;
	private String s2;
	private String s3;
	private String s4;

	@Before
	/**
	 * setup submission files
	 */
	public void setUp() {
		s1 = pythonToStringParser.readFile(getFilePath("submission1.py")).trim();
		s2 = pythonToStringParser.readFile(getFilePath("submission2.py")).trim();
		s3 = pythonToStringParser.readFile(getFilePath("submission3.py")).trim();
		s4 = pythonToStringParser.readFile(getFilePath("submission4.py")).trim();
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
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s1, s2);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s1,s2).length())*100;
    	assertEquals(20.89, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission1.py and submission3.py
	 */
	@Test
	public void compares1s3Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s1,s3);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s1,s3).length())*100;
    	assertEquals(23.63, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission1.py and submission4.py
	 */
	@Test
	public void compares1s4Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s1,s4);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s1,s4).length())*100;
    	assertEquals(14.41, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission2.py and submission3.py
	 */
	@Test
	public void compares2s3Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s2,s3);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s2,s3).length())*100;
    	assertEquals(19.76, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission2.py and submission4.py
	 */
	@Test
	public void compares2s4Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s2, s4);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s2,s4).length())*100;
    	assertEquals(23.15, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure by getting submission files
	 */
	@Test
	public void compares3s4Test() {
    	double similarityMeasure = levenshteinDistanceObj.calculateSimilarity(
				getFilePath("submission3.py"), getFilePath("submission4.py"));
    	assertEquals(12.26, similarityMeasure, 0.01);
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
		double res = levenshteinDistanceObj.calculateSimilarity(
				getFilePath("submission3.py"), getFilePath("submission4.py"));
		assertEquals(12.26, res, 0.01);
	}

}
