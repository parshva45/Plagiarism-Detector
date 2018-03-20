package edu.northeastern.cs5500.controllers.strategies;

import edu.northeastern.cs5500.parsers.PythonToStringParser;
import edu.northeastern.cs5500.strategies.LevenshteinDistance;
import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Praveen Singh
 */
@RestController
@RequestMapping("/strategy/levenshtein")
public class LevenshteinDistanceController {

    private final SimilarityStrategy similarityStrategy;

    @Autowired
    public LevenshteinDistanceController(LevenshteinDistance levenshteinDistance) {
        this.similarityStrategy = levenshteinDistance;
    }

    @RequestMapping(path = "/compare2files", method = RequestMethod.GET)
    public JSONObject getSimilarityBetweenGivenFiles(
            @RequestParam(name = "firstFile") String firstFile,
            @RequestParam(name = "secondFile") String secondFile) {
        Map<String, String> resultMap = new HashMap<>();

        double similarity = similarityStrategy.calculateSimilarity(firstFile, secondFile);

        resultMap.put("similarity", String.valueOf(similarity));
        resultMap.put("response-code", "OK");
        resultMap.put("firstFile", firstFile);
        resultMap.put("secondFile", secondFile);
        return new JSONObject(resultMap);

    }
}
