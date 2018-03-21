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

public class LCSTest extends Cs5500PlagiarismDetectorTeam207ApplicationTests{
	
	@Autowired
	PythonToStringParser pythonToStringParser;
	
	@Autowired
	LCS lcs;

	private String s1;
	private String s2;
	private String s3;
	private String s4;

	@Before
	public void setUp() {
		s1 = pythonToStringParser.readFile(getFilePath("submission1.py")).trim();
		s2 = pythonToStringParser.readFile(getFilePath("submission2.py")).trim();
		s3 = pythonToStringParser.readFile(getFilePath("submission3.py")).trim();
		s4 = pythonToStringParser.readFile(getFilePath("submission4.py")).trim();
	}

	public String getFilePath(String fileName){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(fileName))
				.getFile());
		return file.getAbsolutePath();
	}

	@Test
	public void compares1s2Test() {
    	double similarityMeasure = lcs.calculateSimilarity(s1, s2);
    	assertEquals(90.338, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares1s3Test() {
		double similarityMeasure = lcs.calculateSimilarity(s1, s3);
    	assertEquals(90.338, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares1s4Test() {
		double similarityMeasure = lcs.calculateSimilarity(s1, s4);
    	assertEquals(86.175, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares2s3Test() {
		double similarityMeasure = lcs.calculateSimilarity(s2, s3);
    	assertEquals(100.0, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares2s4Test() {
		double similarityMeasure = lcs.calculateSimilarity(s2, s4);
    	assertEquals(95.392, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares3s4Test() {
    	double similarityMeasure = lcs.calculateSimilarity(
				getFilePath("submission3.py"), getFilePath("submission4.py"));
    	assertEquals(95.392, similarityMeasure, 0.01);
	}

	@Test
	public void exceptionTest() {
		pythonToStringParser.readFile("submission5.py");
	}

	@Test
	public void calculateSimilarityShouldGiveCorrectResult(){
		double res = lcs.calculateSimilarity(
				getFilePath("submission3.py"), getFilePath("submission4.py"));
		assertEquals(95.392, res, 0.01);
	}

}
