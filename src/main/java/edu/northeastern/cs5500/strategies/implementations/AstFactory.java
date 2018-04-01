package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.strategies.implementations.ast.pythonast.*;

import java.io.File;
import java.io.IOException;

/**
 * @author namratabilurkar
 */

public class AstFactory {

    /**
     * Method to create an AST of the input file
     * @param file1 is the path to the file whose AST needs to be generated
     * @return the AST of the input file in the form of a string.
     */
    public String makeAST(String file1) throws IOException {
        ParserFacade parserFacade = new ParserFacade();
        AstBuilder astBuilder = new AstBuilder();
        String abstactSyntaxTree = astBuilder.build(parserFacade.parse(new File(file1)));
        return abstactSyntaxTree;
    }

}
