package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.AstBuilder;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.ParserFacade;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.tree.*;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.editDistance.*;
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
public class AstTreeEditDistance implements SimilarityStrategy {

    @Autowired
    AstBuilder astBuilder1;

    @Autowired
    AstBuilder astBuilder2;

    @Autowired
    ParserFacade parserFacade;

    private static final Logger LOGGER = LogManager.getLogger(AstTreeEditDistance.class);

    /**
     * Parameterized constructor
     * @param astBuilder1 builder for first file
     * @param astBuilder2 builder for second file
     * @param parserFacade parser to create the ASTs
     */
    public AstTreeEditDistance(AstBuilder astBuilder1, AstBuilder astBuilder2, ParserFacade parserFacade) {
        this.astBuilder1 = astBuilder1;
        this.astBuilder2 = astBuilder2;
        this.parserFacade = parserFacade;
    }

    /**
     * Calculate the similarity score using the tree edit distance
     * @param file1 String path of file 1
     * @param file2 String path of file 2
     * @return the similarity score
     */
    @Override
    public double calculateSimilarity(String file1, String file2) {

        String ast1, ast2;

        try {
            ast1 = astBuilder1.build(parserFacade.parse(new File(file1)));
            ast2 = astBuilder2.build(parserFacade.parse(new File(file2)));

            int ast1Length = ast1.length();
            int ast2Length = ast2.length();

            LblTree tree1 = LblTree.fromString(ast1);
            LblTree tree2 = LblTree.fromString(ast2);

            EditDist editDist = new EditDist(1, 1, 1, false);
            double treeDistance = editDist.treeDist(tree1, tree2);
            int maxAstLength = Math.max(ast1Length, ast2Length);

            double similarityScore = ((maxAstLength - treeDistance)/maxAstLength) * 100;
            return similarityScore;

        } catch (IOException e) {
            LOGGER.error("Failed to get Similarity for input file {} and {}", file1, file2);
        }

        return 0.0;
    }
}
