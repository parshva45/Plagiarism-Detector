package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.AstBuilder;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.ParserFacade;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.editDistance.EditDist;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.tree.LblTree;
import edu.northeastern.cs5500.strategies.implementations.moss.MossComparison;
import edu.northeastern.cs5500.strategies.implementations.moss.ResultScraper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author namratabilurkar
 */

@Component
@Scope("prototype")
public class AstTreeEditDistance implements SimilarityStrategy {
    private static final Logger LOGGER = LogManager.getLogger(AstTreeEditDistance.class);

    private final ResultScraper resultScraper;
    private final MossComparison mossComparison;

    @Autowired
    public AstTreeEditDistance(ResultScraper resultScraper,
                                    MossComparison mossComparison) {
        this.resultScraper = resultScraper;
        this.mossComparison = mossComparison;
    }

    /**
     * Calculate the similarity score using the tree edit distance
     * @param file1 String path of file 1
     * @param file2 String path of file 2
     * @return the similarity score
     */
    @Override
    public double calculateSimilarity(String file1, String file2) {

        String ast1;
        String ast2;

        try {
            ast1 = new AstBuilder().build(new ParserFacade().parse(new File(file1)));
            ast2 = new AstBuilder().build(new ParserFacade().parse(new File(file2)));

            int ast1Length = ast1.length();
            int ast2Length = ast2.length();

            LblTree tree1 = LblTree.fromString(ast1);
            LblTree tree2 = LblTree.fromString(ast2);

            EditDist editDist = new EditDist(1, 1, 1, false);
            double treeDistance = editDist.treeDist(tree1, tree2);
            int maxAstLength = Math.max(ast1Length, ast2Length);

            return ((maxAstLength - treeDistance)/maxAstLength) * 100;

        } catch (IOException e) {
            LOGGER.error("Failed to get Similarity for input file {} and {}", file1, file2);
        }

        return 0.0;
    }

    @Override
    public Integer[][] getSimilarLineNos(String file1, String file2) {
        String url = mossComparison.mossPlagiarismUrlForFiles(file1, file2);
        resultScraper.startScraping(url + "/match0-top.html");
        return resultScraper.getMatching();
    }
}
