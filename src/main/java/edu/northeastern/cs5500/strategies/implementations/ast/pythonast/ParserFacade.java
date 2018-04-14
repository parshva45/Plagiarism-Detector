package edu.northeastern.cs5500.strategies.implementations.ast.pythonast;

import edu.northeastern.cs5500.strategies.implementations.ast.pythonparser.Python3Lexer;
import edu.northeastern.cs5500.strategies.implementations.ast.pythonparser.Python3Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static edu.northeastern.cs5500.strategies.implementations.ast.pythonparser.Python3Parser.File_inputContext;

/**
 * @author namratabilurkar
 */
public class ParserFacade {

    private static String readFile(File file, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, encoding);
    }

    public File_inputContext parse(File file) throws IOException {
        String code = readFile(file, Charset.forName("UTF-8"));
        Python3Lexer lexer = new Python3Lexer(new ANTLRInputStream(code));

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        Python3Parser parser = new Python3Parser(tokens);

        return parser.file_input();
    }
}
