package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.implementations.ast.lcs.LongestCommonSubSequence;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.AstBuilder;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.ParserFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author namratabilurkar
 */

@Component
public class TreeEditDistance implements SimilarityStrategy {

    private static final Logger LOGGER = LogManager.getLogger(LongestCommonSubSequence.class);

    @Autowired
    AstBuilder astBuilder;

    @Autowired
    ParserFacade parserFacade;


    /**
     * Parameterized constructor
     * @param astBuilder is the object used to build the ast
     * @param parserFacade is the object used to parse the file to create the ast
     */
    @Autowired
    public TreeEditDistance(AstBuilder astBuilder, ParserFacade parserFacade) {
        this.astBuilder = astBuilder;
        this.parserFacade = parserFacade;
    }

    /**
     * Calculate the similarity of two files using tree edit distance
     * @param file1 String path of file1
     * @param file2 String path of file2
     * @return the similarity score of the two files using tree edit distance
     */
    @Override
    public double calculateSimilarity(String file1, String file2) {

        String ast1, ast2;
        try {
            ast1 = astBuilder.build(parserFacade.parse(new File(file1)));
            ast2 = astBuilder.build(parserFacade.parse(new File(file2)));

            Tree tree1 = new Tree(ast1);
            Tree tree2 = new Tree(ast2);

            int ast1Length = ast1.length();
            int ast2Length = ast2.length();

            int treeEditDistance = Tree.zhangShasha(tree1, tree2);
            return (((double) treeEditDistance / Math.max(ast1Length, ast2Length)) * 100);

        } catch (IOException e) {
            LOGGER.error("Failed to get Similarity for input file {} and {}", file1, file2);
        }

        return 0.0;
    }
}
