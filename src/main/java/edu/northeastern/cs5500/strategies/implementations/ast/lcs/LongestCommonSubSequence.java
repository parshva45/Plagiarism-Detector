package edu.northeastern.cs5500.strategies.implementations.ast.lcs;

import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.AstBuilder;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.ParserFacade;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author namratabilurkar
 */

@Component
@Scope("prototype")
public class LongestCommonSubSequence implements SimilarityStrategy{
    private static final Logger LOGGER = LogManager.getLogger(LongestCommonSubSequence.class);

    private final ParserFacade parserFacade;

    private final AstBuilder astBuilder;

    private final PythonToStringParser pythonToStringParser;


    @Autowired
    public LongestCommonSubSequence(ParserFacade parserFacade, AstBuilder astBuilder,
                                    PythonToStringParser pythonToStringParser) {
        this.parserFacade = parserFacade;
        this.astBuilder = astBuilder;
        this.pythonToStringParser = pythonToStringParser;
    }

    /**
     * Determine the longest common subsequence length
     * @param ast1
     * @param ast2
     * @return
     */
    private int[] lcsLength(String ast1, String ast2) {

        int lengthOfAst1 = ast1.length();
        int lengthOfAst2 = ast2.length();

        int[] lcsLengthAndBase = new int[2];

        int L[][] = new int[lengthOfAst1+1][lengthOfAst2+1];


        for (int i=0; i<=lengthOfAst1; i++) {
            for (int j=0; j<=lengthOfAst2; j++) {
                if (i==0 || j==0) {
                    L[i][j] = 0;
                } else if (ast1.charAt(i-1) == ast2.charAt(j-1)) {
                    L[i][j] = L[i-1][j-1] + 1;
                } else {
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
                }
            }
        }

        lcsLengthAndBase[0] = L[lengthOfAst1][lengthOfAst2];
        lcsLengthAndBase[1] = Math.max(lengthOfAst1, lengthOfAst2);

        return lcsLengthAndBase;
    }

    /**
     * Calculate the similarity of two files using LCS
     * @param file1 String path of file1
     * @param file2 String path of file2
     * @return the similarity score of the two files using LCS
     */
    @Override
    public double calculateSimilarity(String file1, String file2) {
        String ast1, ast2;
        try {
            ast1 = astBuilder.build(parserFacade.parse(new File(file1)));
            ast2 = astBuilder.build(parserFacade.parse(new File(file2)));
            int[] lcsValues = lcsLength(ast1, ast2);
            return (((double) lcsValues[0] / lcsValues[1]) * 100);
        } catch (IOException e) {
            LOGGER.error("Failed to get Similarity for input file {} and {}", file1, file2);
        }
        return 0.0;
    }

}
