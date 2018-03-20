package edu.northeastern.cs5500.controllers.strategies;

import edu.northeastern.cs5500.service.FileComparisonService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.*;

/**
 * @author Praveen Singh
 */
@RestController
@RequestMapping("api/strategy/")
public class FileComparisonController {

    private final FileComparisonService fileComparisonService;

    @Autowired
    public FileComparisonController(FileComparisonService fileComparisonService) {
        this.fileComparisonService = fileComparisonService;
    }

    @RequestMapping(path = "/listStrategies", method = RequestMethod.GET)
    public JSONObject getListOfStrategies(){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("strategies", fileComparisonService.getAllStrategies());
        resultMap.put("response-code", "OK");
        return new JSONObject(resultMap);
    }

    @RequestMapping(path = "/compare2filesByStrategy", method = RequestMethod.GET)
    public JSONObject getSimilarityBetweenGivenFiles(
            @RequestParam(name = "strategy") String strategy,
            @RequestParam(name = "firstFile") String firstFile,
            @RequestParam(name = "secondFile") String secondFile) {


        double similarity = fileComparisonService
                .compareTwoFilesByGivenStrategy(strategy, firstFile, secondFile);

        return prepareResponseJson(strategy, firstFile, secondFile,
                valueOf(similarity), "OK");

    }

    private JSONObject prepareResponseJson(String strategy, String firstFile,
                                           String secondFile, String similarity,
                                           String status){
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("similarity", valueOf(similarity));
        resultMap.put("strategy", strategy);
        resultMap.put("response-code", status);
        resultMap.put("firstFile", firstFile);
        resultMap.put("secondFile", secondFile);
        return new JSONObject(resultMap);
    }
}
