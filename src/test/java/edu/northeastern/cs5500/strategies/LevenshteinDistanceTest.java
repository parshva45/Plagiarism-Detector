package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.Cs5500PlagiarismDetectorTeam207ApplicationTests;
import edu.northeastern.cs5500.service.CompareFilesLevenshtein;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class LevenshteinDistanceTest extends Cs5500PlagiarismDetectorTeam207ApplicationTests{
	
	@Autowired
	CompareFilesLevenshtein compareFilesLevenshtein;
	
	@Autowired
	LevenshteinDistance levenshteinDistanceObj;

	private String s1;
	private String s2;
	private String s3;
	private String s4;

	@Before
	public void setUp() {
		s1 = compareFilesLevenshtein.readFile(getFilePath("submission1.py")).trim();
		s2 = compareFilesLevenshtein.readFile(getFilePath("submission2.py")).trim();
		s3 = compareFilesLevenshtein.readFile(getFilePath("submission3.py")).trim();
		s4 = compareFilesLevenshtein.readFile(getFilePath("submission4.py")).trim();
	}

	public String getFilePath(String fileName){
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(fileName))
				.getFile());
		return file.getAbsolutePath();
	}

	@Test
	public void compares1s2Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s1, s2);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s1,s2).length())*100;
    	assertEquals(90.338, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares1s3Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s1,s3);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s1,s3).length())*100;
    	assertEquals(90.338, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares1s4Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s1,s4);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s1,s4).length())*100;
    	assertEquals(86.175, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares2s3Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s2,s3);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s2,s3).length())*100;
    	assertEquals(100.0, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares2s4Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s2, s4);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s2,s4).length())*100;
    	assertEquals(95.392, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares3s4Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s3,s4);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s3,s4).length())*100;
    	assertEquals(95.392, similarityMeasure, 0.01);
	}

	@Test
	public void exceptionTest() {
		compareFilesLevenshtein.readFile("submission5.py");
	}


}
