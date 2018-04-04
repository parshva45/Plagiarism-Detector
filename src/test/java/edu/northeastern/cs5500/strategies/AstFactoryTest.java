package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.strategies.implementations.AstFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author namratabilurkar 
 */

public class AstFactoryTest {

//    static String path1 = "/Users/namratabilurkar/Academics/MSD/Project/team-207/src/test/resources/simple.py";

    private static String file1;

    /**
     * setup submission files
     */
    @Before
    public void setUp() {
        file1 = getFilePath("simple.py");
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
     * Testing the ast built for a sample file
     * @throws IOException
     */
    @Test
    public void Test1() throws IOException {
    	String ast = "file_input\n" +
                "  funcdef\n" +
                "    parameters\n" +
                "      typedargslist\n" +
                "        tfpdef\n" +
                "        tfpdef\n" +
                "    suite\n" +
                "      simple_stmt\n" +
                "        return_stmt\n" +
                "          arith_expr\n" +
                "            atom\n" +
                "            atom\n" +
                "  simple_stmt\n" +
                "    power\n" +
                "      atom\n" +
                "      trailer\n" +
                "        term\n" +
                "          string\n" +
                "          atom\n" +
                "            testlist_comp\n" +
                "              integer\n" +
                "              integer\n" +
                "              power\n" +
                "                atom\n" +
                "                trailer\n" +
                "                  arglist\n" +
                "                    integer\n" +
                "                    integer\n" +
                "  funcdef\n" +
                "    parameters\n" +
                "      typedargslist\n" +
                "        tfpdef\n" +
                "        tfpdef\n" +
                "    suite\n" +
                "      simple_stmt\n" +
                "        return_stmt\n" +
                "          arith_expr\n" +
                "            atom\n" +
                "            atom\n";
        String ast1;
        AstFactory astFactory = new AstFactory();
        ast1 = astFactory.makeAST(AstFactoryTest.file1);
        Assert.assertEquals(ast, ast1);
    }

}
