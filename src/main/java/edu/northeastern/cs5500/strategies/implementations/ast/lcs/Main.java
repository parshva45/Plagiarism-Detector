package edu.northeastern.cs5500.strategies.implementations.ast.lcs;

import java.io.IOException;

/**
 * @author namratabilurkar
 */

public class Main {

    public static void main(String[] args) throws IOException {

        //String tree1Str1 = "f(d(a c(b)) e)";
        //String tree1Str2 = "f(c(d(a b)) e)";
        String tree1Str1 = "f(d(a c(b)) e)";
        String tree1Str2 = "f(d(a))";

        LongestCommonSubSequence s1 = new LongestCommonSubSequence();
        double distance = s1.calculateSimilarity(tree1Str1, tree1Str2);

        System.out.println(distance);
    }
}
