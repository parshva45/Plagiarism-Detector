package edu.northeastern.cs5500.strategies.implementations.moss;

import java.util.ArrayList;
import java.util.List;

/**
 * @author namratabilurkar
 */

public class MatchStorerMap {

//    private String fileName;
////    private List<LineMatchMap> listOfMatchingLineNums;
//    private List<Integer> listOfMatchingLineNums = new ArrayList<Integer>();
//
////    public List<LineMatchMap> getListOfMatchingLineNums() {
////        return listOfMatchingLineNums;
////    }
//
//
//    public List<Integer> getListOfMatchingLineNums() {
//        return listOfMatchingLineNums;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
////    public void setListOfMatchingLineNums(List<LineMatchMap> listOfMatchingLineNums) {
////        this.listOfMatchingLineNums = listOfMatchingLineNums;
////    }
//
//
//    public void setListOfMatchingLineNums(List<Integer> listOfMatchingLineNums) {
//        this.listOfMatchingLineNums = listOfMatchingLineNums;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    @Override
//    public String toString() {
//        return "Filename: " + fileName + "\nListOfMatchingLineNums: " + listOfMatchingLineNums;
//    }
    private Integer[][] matchingLineNums;

    public Integer[][] getMatchingLineNums() {
        return matchingLineNums;
    }

    public void setMatchingLineNums(Integer[][] matchingLineNums) {
        this.matchingLineNums = matchingLineNums;
    }

    @Override
    public String toString() {
        for (int i=0; i<matchingLineNums[0].length; i++)  {
            for (int j=0; j<matchingLineNums[1].length; j++) {
                System.out.println(matchingLineNums[i][j].toString());
            }
        }
        return "Matching line numbers: " + matchingLineNums.toString();
    }
}
