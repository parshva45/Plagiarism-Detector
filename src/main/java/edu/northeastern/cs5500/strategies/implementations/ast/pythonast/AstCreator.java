package edu.northeastern.cs5500.strategies.implementations.ast.pythonast;

/**
 * @author namratabilurkar
 */

import java.io.File;
import java.io.IOException;

public class AstCreator {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ParserFacade parserFacade = new ParserFacade();
        //AstPrinter astPrinter = new AstPrinter();
        AstBuilder astBuilder = new AstBuilder();
        //astPrinter.print(parserFacade.parse(new File("examples/simple.py")));
        //astPrinter.print(parserFacade.parse(new File("examples/render.py")));
        //String ast1 = astBuilder.build(parserFacade.parse(new File("examples/simple.py")));
        String path1 = "/Users/namratabilurkar/Academics/MSD/Project/team-207/src/test/resources/simple.py";
        //astPrinter.print(parserFacade.parse(new File(path1)));
        String ast1 = astBuilder.build(parserFacade.parse(new File(path1)));
        System.out.println(ast1);
    }
}

