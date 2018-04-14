package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.FileComparisonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

/**
 * @author Praveen Singh
 */
@RestController
@RequestMapping("api/comparison/")
public class FileComparisonController {
    private static final Logger LOGGER = LogManager.getLogger(FileComparisonController.class);

    private final FileComparisonService fileComparisonService;

    @Autowired
    public FileComparisonController(FileComparisonService fileComparisonService) {
        this.fileComparisonService = fileComparisonService;
    }

    /**
     * Rest Controller to list the different types of strategies present in the system
     * @return List of String
     */
    @RequestMapping(path = "/listStrategies", method = RequestMethod.GET)
    public JSONObject getListOfStrategies(){
        LOGGER.info("executing method getListOfStrategies ");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("strategies", fileComparisonService.getAllStrategies());
        resultMap.put("response-code", "OK");
        LOGGER.info("executing method getListOfStrategies successfully");
        return new JSONObject(resultMap);
    }

    /**
     * Rest Controller to list the different types of strategies present in the system
     * @return List of String
     */
    @RequestMapping(path = "/getCount", method = RequestMethod.GET)
    public int getCount(){
        LOGGER.info("executing method getCount ");
        int data = fileComparisonService.getCount();
        LOGGER.info("executed method getCount successfully");
        return data;
    }

    /**
     * Rest End point that takes in the path of two files and returns the simmilarity
     * between them based on given strategy.
     * @param strategy String
     * @param firstFile String
     * @param secondFile String
     * @return JSONObject having the similarity percentage.
     */
    @RequestMapping(path = "/compare2filesByStrategy", method = RequestMethod.GET)
    public JSONObject getSimilarityBetweenGivenFiles(
            @RequestParam(name = "strategy") String strategy,
            @RequestParam(name = "firstFile") String firstFile,
            @RequestParam(name = "secondFile") String secondFile) {

        LOGGER.info("executing method getSimilarityBetweenGivenFiles with" +
                "with parameter strategy={} firstFile={} secondFile={}",
                strategy, firstFile, secondFile);
        double similarity = fileComparisonService
                .compareTwoFilesByGivenStrategy(strategy, firstFile, secondFile);
        int[][] lineNumbers = fileComparisonService
                .findLineNumbersByGivenStrategy(strategy, firstFile, secondFile);

        LOGGER.info("getSimilarityBetweenGivenFiles API returned data successfully.");
        return prepareResponseJson(strategy, firstFile, secondFile,
                valueOf(similarity), lineNumbers, "OK");

    }

    /**
     * Private helper method to create the JSON Response for the API;s
     * @param strategy String
     * @param firstFile String
     * @param secondFile String
     * @param similarity double
     * @param status String
     * @return JSONObject
     */
    private JSONObject prepareResponseJson(String strategy, String firstFile,
                                           String secondFile, String similarity,
                                           int[][] lineNumbers, String status){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("similarity", valueOf(similarity));
        resultMap.put("lineNumbers", lineNumbers);
        resultMap.put("strategy", strategy);
        resultMap.put("response-code", status);
        resultMap.put("firstFile", firstFile);
        resultMap.put("secondFile", secondFile);
        return new JSONObject(resultMap);
    }

    /**
     * Rest End point that takes in the path of two files and returns the similarity
     * between them based on All the strategies.
     * @param firstFile String
     * @param secondFile String
     * @return JSONObject having the similarity percentage.
     */
    @RequestMapping(path = "/compareByAllStrategies", method = RequestMethod.GET)
    public JSONObject getSimilarityBetweenGivenFilesByAllStrategies(
            @RequestParam(name = "firstFile") String firstFile,
            @RequestParam(name = "secondFile") String secondFile) {

        LOGGER.info("executing method compareByAllStrategies with with " +
                        "parameter firstFile={} secondFile={}", firstFile, secondFile);

        Map<String, Double> result = fileComparisonService.getSimilarityByAllMethods(firstFile, secondFile);

        LOGGER.info("compareByAllStrategies API returned data successfully.");
        return new JSONObject(result);
    }
}
