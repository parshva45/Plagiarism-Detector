package edu.northeastern.cs5500.strategies.levenshtein;

import static org.junit.Assert.*;

import org.junit.Test;

public class LevenshteinDistanceTests {
	
	LevenshteinDistance ld = new LevenshteinDistance();
	public static final String DIR = System.getProperty("user.dir")+"\\src\\test\\java\\edu\\northeastern\\cs5500\\strategies\\levenshtein\\submission\\";
    String s1 = ld.readFile(DIR+"submission1.py").trim();
	String s2 = ld.readFile(DIR+"submission2.py").trim();
	String s3 = ld.readFile(DIR+"submission3.py").trim();
	String s4 = ld.readFile(DIR+"submission4.py").trim();

	@Test
	public void compares1s2Test() {
		LevenshteinDistance ld1 = new LevenshteinDistance(s1,s2);
		int levenshteinDistance = ld1.getDistance();
    	double similarityMeasure = (1-(double)levenshteinDistance/ld1.longer(s1,s2).length())*100;
    	assertEquals(90.338, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares1s3Test() {
		LevenshteinDistance ld2 = new LevenshteinDistance(s1,s3);
		int levenshteinDistance = ld2.getDistance();
    	double similarityMeasure = (1-(double)levenshteinDistance/ld2.longer(s1,s3).length())*100;
    	assertEquals(90.338, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares1s4Test() {
		LevenshteinDistance ld3 = new LevenshteinDistance(s1,s4);
		int levenshteinDistance = ld3.getDistance();
    	double similarityMeasure = (1-(double)levenshteinDistance/ld3.longer(s1,s4).length())*100;
    	assertEquals(86.175, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares2s3Test() {
		LevenshteinDistance ld4 = new LevenshteinDistance(s2,s3);
		int levenshteinDistance = ld4.getDistance();
    	double similarityMeasure = (1-(double)levenshteinDistance/ld4.longer(s2,s3).length())*100;
    	assertEquals(100.0, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares2s4Test() {
		LevenshteinDistance ld5 = new LevenshteinDistance(s2,s4);
		int levenshteinDistance = ld5.getDistance();
    	double similarityMeasure = (1-(double)levenshteinDistance/ld5.longer(s2,s4).length())*100;
    	assertEquals(95.392, similarityMeasure, 0.01);
	}
	
	@Test
	public void compares3s4Test() {
		LevenshteinDistance ld6 = new LevenshteinDistance(s3,s4);
		int levenshteinDistance = ld6.getDistance();
    	double similarityMeasure = (1-(double)levenshteinDistance/ld6.longer(s3,s4).length())*100;
    	assertEquals(95.392, similarityMeasure, 0.01);
	}

	@Test
	public void exceptionTest() {
		ld.readFile(DIR+"submission5.py");
	}


}
