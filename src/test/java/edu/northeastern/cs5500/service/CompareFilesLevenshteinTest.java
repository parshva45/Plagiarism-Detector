package edu.northeastern.cs5500.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Praveen Singh
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompareFilesLevenshteinTest {

    @Autowired
    CompareFilesLevenshtein compareFilesLevenshtein;

    @Test
    public void test(){
        compareFilesLevenshtein.readFile("hello.txt");
    }

}