package edu.northeastern.cs5500.strategies.implementations.ast;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import edu.northeastern.cs5500.strategies.implementations.ast.lcs.LongestCommonSubSequence;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ASTFactory {

    private final LongestCommonSubSequence longestCommonSubSequence;
    private final Tree tree;

    @Autowired
    public ASTFactory(LongestCommonSubSequence longestCommonSubSequence, Tree tree) {
        this.longestCommonSubSequence = longestCommonSubSequence;
        this.tree = tree;
    }


    /*public void getStrategyByStrategyType(String strategy){
        if(StrategyTypes.L.toString().equals(strategy)){
            return levenshteinDistance;
        }
        if(StrategyTypes.LCS.toString().equals(strategy)){
            return lcs;
        }
        return levenshteinDistance;
    }*/
}
