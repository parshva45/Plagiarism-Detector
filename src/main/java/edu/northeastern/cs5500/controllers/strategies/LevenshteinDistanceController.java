package edu.northeastern.cs5500.controllers.strategies;

import edu.northeastern.cs5500.service.CompareFilesLevenshtein;
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

    private final CompareFilesLevenshtein compareFilesLevenshtein;

    @Autowired
    public LevenshteinDistanceController(CompareFilesLevenshtein compareFilesLevenshtein) {
        this.compareFilesLevenshtein = compareFilesLevenshtein;
    }

    @RequestMapping(path = "/compare2files", method = RequestMethod.GET)
    public JSONObject getSimilarityBetweenGivenFiles(
            @RequestParam(name = "firstFile") String firstFile,
            @RequestParam(name = "secondFile") String secondFile) {
        Map<String, String> resultMap = new HashMap<>();

        double similarity = compareFilesLevenshtein.getSimilarityBetweenFiles(firstFile, secondFile);

        resultMap.put("similarity", String.valueOf(similarity));
        resultMap.put("response-code", "OK");
        resultMap.put("firstFile", firstFile);
        resultMap.put("secondFile", secondFile);
        return new JSONObject(resultMap);

    }
}
