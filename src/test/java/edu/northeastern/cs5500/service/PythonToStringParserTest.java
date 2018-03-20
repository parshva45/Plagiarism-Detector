package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.parsers.PythonToStringParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Praveen Singh
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PythonToStringParserTest {

    @Autowired
    PythonToStringParser pythonToStringParser;

    @Test
    public void test(){
        pythonToStringParser.readFile("hello.txt");
    }

}