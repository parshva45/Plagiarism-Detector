package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.Cs5500PlagiarismDetectorTeam207ApplicationTests;
import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.implementations.LevenshteinDistance;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    	assertEquals(20.65, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission1.py and submission3.py
	 */
	@Test
	public void compares1s3Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s1,s3);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s1,s3).length())*100;
    	assertEquals(23.11, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission1.py and submission4.py
	 */
	@Test
	public void compares1s4Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s1,s4);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s1,s4).length())*100;
    	assertEquals(14.07, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission2.py and submission3.py
	 */
	@Test
	public void compares2s3Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s2,s3);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s2,s3).length())*100;
    	assertEquals(19.69, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure between submission2.py and submission4.py
	 */
	@Test
	public void compares2s4Test() {
		int levenshteinDistance = levenshteinDistanceObj.getDistance(s2, s4);
    	double similarityMeasure = (1-(double)levenshteinDistance/levenshteinDistanceObj
				.longer(s2,s4).length())*100;
    	assertEquals(23.47, similarityMeasure, 0.01);
	}
	
	/**
	 * test similarity measure by getting submission files
	 */
	@Test
	public void compares3s4Test() {
    	double similarityMeasure = levenshteinDistanceObj.calculateSimilarity(
				getFilePath("submission3.py"), getFilePath("submission4.py"));
    	assertEquals(11.93, similarityMeasure, 0.01);
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
		assertEquals(11.93, res, 0.01);
	}

    /**
     * test similarity line numbers between crawler.py and focused_crawler.py
     */
    @Test
    public void linesWhenComparingaddOrDeleteRedundantElementsPlagiarismType() {
        Integer[][] lineNumbers = levenshteinDistanceObj.getSimilarLineNos(
                getFilePath("crawler.py"), getFilePath("focused_crawler.py"));
        Integer[] expectedLines1 = {0, 1, 2, 3, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9, 10, 11, 12, 13, 14, -1, 16, 17, 18, 19, 20, -1, 22, 23, 24, 25, -1, 27, -1, 29, 30, 31, -1, -1, -1, -1, -1, -1, 37, -1, 39, 40, -1, 42, 43, 44, 45, -1, 47, 48, 49, 50, -1, 52, -1, 54, -1, 58, 59, -1, 61, 62, 63, 64, -1, 66, -1, 68, 69, -1, 71, 72, -1, 74, 75, 76, 77, -1, 79, 80, -1, 82, 83, -1, 85, 86, 87, -1, -1, -1, -1, -1, 89, 90, -1, 92, 93, 94, -1, 96, -1, -1, 99, 100, -1, 102, 103, 104, 105, -1, 107, -1, -1, -1, 112, 113, -1, 115, 116, 117, 118, -1, 120, 121, 122, -1, 124, 125, -1, 127, -1, -1, 130, 131, -1, 133, 134, 135, 136, -1, 138, -1, -1, -1, 143, 144, -1, 146, 147, 148, 149, -1, -1, 152, 153, 154, 155, 156, 157, -1, 159, 160, -1, 162, 163, 164, 165, 166, 167, 168, 169, 170, -1, -1, 173, 174, 175, 176, -1, 178, -1, 180, 181, -1, 183, -1, -1};
        Integer[] expectedLines2 = {0, 1, 2, 3, 4, -1, -1, -1, -1, 22, 23, 24, 25, 26, 27, -1, 29, 30, 31, 32, 33, -1, 35, 36, 37, 38, -1, 40, -1, 42, 43, 44, -1, -1, -1, -1, -1, 51, -1, 53, 54, -1, 56, 57, 58, 59, -1, 61, 62, 63, 64, -1, 66, -1, 68, -1, -1, -1, 70, 71, -1, 73, 74, 75, 76, -1, 78, -1, 80, 81, -1, 83, 84, -1, 86, 87, 88, 89, -1, 91, 92, -1, 94, 95, -1, 97, 98, 99, -1, 105, 106, -1, 108, 109, 110, -1, 112, -1, -1, 115, 116, -1, 118, 119, 120, 121, -1, 123, -1, -1, -1, -1, 127, 128, -1, 130, 131, 132, 133, -1, 135, 136, 137, -1, 139, 140, -1, 142, -1, -1, 145, 146, -1, 148, 149, 150, 151, -1, 153, -1, -1, -1, -1, 157, 158, -1, 160, 161, 162, 163, -1, -1, 166, 167, 168, 169, 170, 171, -1, 173, 174, -1, 176, 177, 178, 179, 180, 181, 182, 183, 184, -1, -1, 187, 188, 189, 190, -1, 192, -1, 194, 195, -1, 197, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        assertArrayEquals(expectedLines1, lineNumbers[0]);
        assertArrayEquals(expectedLines2, lineNumbers[1]);
    }

    /**
     * test similarity line numbers between focused_crawler.py and crawler.py
     */
    @Test
    public void linesWhenComparingaddOrDeleteRedundantElementsPlagiarismTypeReverse() {
        Integer[][] lineNumbers = levenshteinDistanceObj.getSimilarLineNos(
                getFilePath("focused_crawler.py"), getFilePath("crawler.py"));
        Integer[] expectedLines1 = {0, 1, 2, 3, 4, -1, -1, -1, -1, 22, 23, 24, 25, 26, 27, -1, 29, 30, 31, 32, 33, -1, 35, 36, 37, 38, -1, 40, -1, 42, 43, 44, -1, -1, -1, -1, -1, 51, -1, 53, 54, -1, 56, 57, 58, 59, -1, 61, 62, 63, 64, -1, 66, -1, 68, -1, -1, -1, 70, 71, -1, 73, 74, 75, 76, -1, 78, -1, 80, 81, -1, 83, 84, -1, 86, 87, 88, 89, -1, 91, 92, -1, 94, 95, -1, 97, 98, 99, -1, 105, 106, -1, 108, 109, 110, -1, 112, -1, -1, 115, 116, -1, 118, 119, 120, 121, -1, 123, -1, -1, -1, -1, 127, 128, -1, 130, 131, 132, 133, -1, 135, 136, 137, -1, 139, 140, -1, 142, -1, -1, 145, 146, -1, 148, 149, 150, 151, -1, 153, -1, -1, -1, -1, 157, 158, -1, 160, 161, 162, 163, -1, -1, 166, 167, 168, 169, 170, 171, -1, 173, 174, -1, 176, 177, 178, 179, 180, 181, 182, 183, 184, -1, -1, 187, 188, 189, 190, -1, 192, -1, 194, 195, -1, 197, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        Integer[] expectedLines2 = {0, 1, 2, 3, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9, 10, 11, 12, 13, 14, -1, 16, 17, 18, 19, 20, -1, 22, 23, 24, 25, -1, 27, -1, 29, 30, 31, -1, -1, -1, -1, -1, -1, 37, -1, 39, 40, -1, 42, 43, 44, 45, -1, 47, 48, 49, 50, -1, 52, -1, 54, -1, 58, 59, -1, 61, 62, 63, 64, -1, 66, -1, 68, 69, -1, 71, 72, -1, 74, 75, 76, 77, -1, 79, 80, -1, 82, 83, -1, 85, 86, 87, -1, -1, -1, -1, -1, 89, 90, -1, 92, 93, 94, -1, 96, -1, -1, 99, 100, -1, 102, 103, 104, 105, -1, 107, -1, -1, -1, 112, 113, -1, 115, 116, 117, 118, -1, 120, 121, 122, -1, 124, 125, -1, 127, -1, -1, 130, 131, -1, 133, 134, 135, 136, -1, 138, -1, -1, -1, 143, 144, -1, 146, 147, 148, 149, -1, -1, 152, 153, 154, 155, 156, 157, -1, 159, 160, -1, 162, 163, 164, 165, 166, 167, 168, 169, 170, -1, -1, 173, 174, 175, 176, -1, 178, -1, 180, 181, -1, 183, -1, -1};
        assertArrayEquals(expectedLines1, lineNumbers[0]);
        assertArrayEquals(expectedLines2, lineNumbers[1]);
    }
	
	/**
	 * test similarity measure for "add or delete redundant elements" type of plagiarism
	 */
	@Test
	public void addOrDeleteRedundantElementsPlagiarismType(){
		double res = levenshteinDistanceObj.calculateSimilarity(
				getFilePath("crawler.py"), getFilePath("focused_crawler.py"));
		assertEquals(76.73, res, 0.01);
	}
	
	/**
	 * test similarity measure for "change comments" type of plagiarism
	 */
	@Test
	public void changeCommentsPlagiarismType(){
		double res = levenshteinDistanceObj.calculateSimilarity(
				getFilePath("page_rank1.py"), getFilePath("page_rank2.py"));
		assertEquals(88.92, res, 0.01);
	}
	
	/**
	 * test similarity measure for "change names" type of plagiarism
	 */
	@Test
	public void changeNamesPlagiarismType(){
		double res = levenshteinDistanceObj.calculateSimilarity(
				getFilePath("crawler1.py"), getFilePath("crawler2.py"));
		assertEquals(91.22, res, 0.01);
	}
	
	/**
	 * test similarity measure for "reorder the code" type of plagiarism
	 */
	@Test
	public void reorderTheCodePlagiarismType(){
		double res = levenshteinDistanceObj.calculateSimilarity(
				getFilePath("student1_submission.zip"), getFilePath("student2_submission.zip"));
		assertEquals(53.25, res, 0.01);
	}
	
	/**
	 * test similarity measure for "rewrite loops" type of plagiarism
	 */
	@Test
	public void rewriteLoopsPlagiarismType(){
		double res = levenshteinDistanceObj.calculateSimilarity(
				getFilePath("indexer1.py"), getFilePath("indexer2.py"));
		assertEquals(94.39, res, 0.01);
	}

	/**
	 * test similarity measure between submission1.zip files and submission2.zip files
	 */
    @Test
    public void compares1s2ZipTest() {
        double res = levenshteinDistanceObj.calculateSimilarity(
                getFilePath("submission1.zip"), getFilePath("submission2.zip"));
        assertEquals(21.86, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission1.zip files and submission3.zip files
	 */
    @Test
    public void compares1s3ZipTest() {
        double res = levenshteinDistanceObj.calculateSimilarity(
                getFilePath("submission1.zip"), getFilePath("submission3.zip"));
        assertEquals(22.06, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission1.zip files and submission4.zip files
	 */
    @Test
    public void compares1s4ZipTest() {
        double res = levenshteinDistanceObj.calculateSimilarity(
                getFilePath("submission1.zip"), getFilePath("submission4.zip"));
        assertEquals(21.39, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission2.zip files and submission3.zip files
	 */
    @Test
    public void compares2s3ZipTest() {
        double res = levenshteinDistanceObj.calculateSimilarity(
                getFilePath("submission2.zip"), getFilePath("submission3.zip"));
        assertEquals(16.64, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission2.zip files and submission4.zip files
	 */
    @Test
    public void compares2s4ZipTest() {
        double res = levenshteinDistanceObj.calculateSimilarity(
                getFilePath("submission2.zip"), getFilePath("submission4.zip"));
        assertEquals(19.42, res, 0.01);
    }
    
    /**
	 * test similarity measure between submission3.zip files and submission4.zip files
	 */
    @Test
    public void compares3s4ZipTest() {
        double res = levenshteinDistanceObj.calculateSimilarity(
                getFilePath("submission3.zip"), getFilePath("submission4.zip"));
        assertEquals(17.85, res, 0.01);
    }

    /**
     * test line numbers similarity between submission3.zip files and submission4.zip files
     */
    @Test
    public void lineNumbersZipTest() {
        Integer[][] res = levenshteinDistanceObj.getSimilarLineNos(
                getFilePath("submission3.zip"), getFilePath("submission4.zip"));
        assertArrayEquals(new int[0][0], res);
    }

    /**
     * test for one .py and another .zip submission
     */
    @Test(expected = IllegalArgumentException.class)
    public void lineNumbersComparesOnePythonOneZipTest() {
        levenshteinDistanceObj.getSimilarLineNos(
                getFilePath("submission3.py"), getFilePath("submission4.zip"));
        fail();
    }

    /**
     * test for one .zip and another .py submission
     */
    @Test(expected = IllegalArgumentException.class)
    public void lineNumbersComparesOneZipOnePythonTest() {
        levenshteinDistanceObj.getSimilarLineNos(
                getFilePath("submission3.py"), getFilePath("submission4.zip"));
        fail();
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
        levenshteinDistanceObj.calculateSimilarity(
                getFilePath("submission3.zip"), getFilePath("submission4.py"));
        fail();
    }
	
	/**
	 * test for one .py and another .zip submission
	 */
	@Test(expected = IllegalArgumentException.class)
    public void comparesOnePythonOneZipTest() {
        levenshteinDistanceObj.calculateSimilarity(
                getFilePath("submission3.py"), getFilePath("submission4.zip"));
        fail();
    }

}
