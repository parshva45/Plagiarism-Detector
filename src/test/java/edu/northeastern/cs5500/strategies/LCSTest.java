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

	private String file1;
	private String file2;
	private String file3;
	private String file4;

	@Before
	public void setUp() {
		file1 = getFilePath("submission1.py");
		file2 = getFilePath("submission2.py");
		file3 = getFilePath("submission3.py");
		file4 = getFilePath("submission4.py");
	}

	public String getFilePath(String fileName){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(fileName))
				.getFile());
		return file.getAbsolutePath();
	}

	@Test
	public void compares1s2Test() {
    	double similarityMeasure = lcs.calculateSimilarity(file1, file2);
    	assertEquals(90.338, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares1s3Test() {
		double similarityMeasure = lcs.calculateSimilarity(file1, file3);
    	assertEquals(90.338, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares1s4Test() {
		double similarityMeasure = lcs.calculateSimilarity(file1, file4);
    	assertEquals(86.175, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares2s3Test() {
		double similarityMeasure = lcs.calculateSimilarity(file2, file3);
    	assertEquals(100.0, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares2s4Test() {
		double similarityMeasure = lcs.calculateSimilarity(file2, file4);
    	assertEquals(95.392, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares3s4Test() {
    	double similarityMeasure = lcs.calculateSimilarity(file3, file4);
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
