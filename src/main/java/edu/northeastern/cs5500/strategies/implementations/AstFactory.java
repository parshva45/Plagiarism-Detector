package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.*;

import java.io.File;
import java.io.IOException;

/**
 * @author namratabilurkar
 */

public class AstFactory {

//    private final LCS lcs;
//    private final LongestCommonSubSequence longestCommonSubSequence;
//    ParserFacade parserFacade = new ParserFacade();
//    AstBuilder astBuilder = new AstBuilder();
    private final ParserFacade parserFacade;
    private final AstBuilder astBuilder;

    public AstFactory() {
        this.parserFacade = new ParserFacade();
        astBuilder = new AstBuilder();
    }

    /**
     * Method to create an AST of the input file
     * @param file1 is the path to the file whose AST needs to be generated
     * @return the AST of the input file in the form of a string.
     */
    public String makeAST(String file1) throws IOException {

        String abstactSyntaxTree = this.astBuilder.build(this.parserFacade.parse(new File(file1)));
        return abstactSyntaxTree;
    }

}
