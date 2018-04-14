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
    	assertEquals(21.83, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission1.py and submission3.py
	 */
	@Test
	public void compares1s3Test() {
		double similarityMeasure = lcs.calculateSimilarity(file1, file3);
    	assertEquals(43.40, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission1.py and submission4.py
	 */
	@Test
	public void compares1s4Test() {
		double similarityMeasure = lcs.calculateSimilarity(file1, file4);
    	assertEquals(14.40, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission2.py and submission3.py
	 */
	@Test
	public void compares2s3Test() {
		double similarityMeasure = lcs.calculateSimilarity(file2, file3);
    	assertEquals(20.79, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission2.py and submission4.py
	 */
	@Test
	public void compares2s4Test() {
		double similarityMeasure = lcs.calculateSimilarity(file2, file4);
    	assertEquals(28.42, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission3.py and submission4.py
	 */
	@Test
	public void compares3s4Test() {
    	double similarityMeasure = lcs.calculateSimilarity(file3, file4);
    	assertEquals(12.73, similarityMeasure, 0.01);
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
		assertEquals(12.73, res, 0.01);
	}
	
	/**
	 * test similarity measure for "add or delete redundant elements" type of plagiarism
	 */
	@Test
	public void addOrDeleteRedundantElementsPlagiarismType(){
		double res = lcs.calculateSimilarity(
				getFilePath("crawler.py"), getFilePath("focused_crawler.py"));
		assertEquals(80.46, res, 0.01);
	}
	
	/**
	 * test similarity measure for "change comments" type of plagiarism
	 */
	@Test
	public void changeCommentsPlagiarismType(){
		double res = lcs.calculateSimilarity(
				getFilePath("page_rank1.py"), getFilePath("page_rank2.py"));
		assertEquals(90.99, res, 0.01);
	}
	
	/**
	 * test similarity measure for "change names" type of plagiarism
	 */
	@Test
	public void changeNamesPlagiarismType(){
		double res = lcs.calculateSimilarity(
				getFilePath("crawler1.py"), getFilePath("crawler2.py"));
		assertEquals(92.62, res, 0.01);
        System.out.println("here");
        int[][] test = lcs.getsimilarLineNos(
                getFilePath("crawler1.py"), getFilePath("crawler2.py"));
        for(int i=0; i<2; i++) {
            for (int j = 0; j < test[i].length; j++) {
                System.out.print((test[i][j] + 1) + ",");
            }
            System.out.println();
        }
	}
	
	/**
	 * test similarity measure for "reorder the code" type of plagiarism
	 */
	@Test
	public void reorderTheCodePlagiarismType(){
		double res = lcs.calculateSimilarity(
				getFilePath("student1_submission.zip"), getFilePath("student2_submission.zip"));
		assertEquals(61.01, res, 0.01);
	}
	
	/**
	 * test similarity measure for "rewrite loops" type of plagiarism
	 */
	@Test
	public void rewriteLoopsPlagiarismType(){
		double res = lcs.calculateSimilarity(
				getFilePath("indexer1.py"), getFilePath("indexer2.py"));
		assertEquals(94.42, res, 0.01);
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
