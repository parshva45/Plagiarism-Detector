package edu.northeastern.cs5500.utils;

public class Constants {

    private Constants(){}

    /**
     * Weights of different strategies.
     */
    public static final double LCS_WEIGHT = 0.25;
    public static final double LEV_WEIGHT = 0.25;
    public static final double AST_LCS_WEIGHT = 0.25;
    public static final double AST_TREE_EDIT_WEIGHT = 0.25;

    public static final String RESULT = "RESULT";
    public static final String RESPONSE_CODE = "response-code";
    public static final int MOSS_ID = 47965740;
    public static final String LANGUAGE = "python";
    public static final String LANGUAGE_CODE = "py";


    public static final String PLAGIARISM_THRESHOLD = "plagiarism.threshhold";

    /**
     * Scraper constants
     */
    public static final String SCRAPING_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 "+
            "(KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    public static final int WAIT_TIME = 20000;
    public static final String STARTING_LINK = "http://moss.stanford.edu/results/";
    public static final int STATUS_CODE_RANGE_UPPER_BOUND = 400;
    public static final int STATUS_CODE_RANGE_LOWER_BOUND = 300;
    public static final int SUCCESS_STATUS_CODE = 200;
}
