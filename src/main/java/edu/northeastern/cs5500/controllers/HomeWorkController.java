package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.HomeWork;
import edu.northeastern.cs5500.service.HomeWorkService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Praveen Singh
 */
@RestController
@RequestMapping("/api/homework")
public class HomeWorkController {
    private static final Logger LOGGER = LogManager.getLogger(HomeWorkController.class);

    private final HomeWorkService homeWorkService;

    @Autowired
    public HomeWorkController(HomeWorkService homeWorkService) {
        this.homeWorkService = homeWorkService;
    }

    /**
     * Rest End point to get list of homeworks for a given course.
     * @param courseId Integer
     * @return JSONObject
     */
    @RequestMapping(path = "/getHomeWorksByCourse/{courseId}", method = RequestMethod.GET)
    public JSONObject getListOfHomeWorksByCourse(@PathVariable int courseId){
        LOGGER.info("getListOfHomeWorksByCourse API called with courseId={}", courseId);
        return createResponse(homeWorkService.getListOfHomeWorkByCourseNo(courseId));
    }

    /**
     * Rest Endpoint to get the homeWork details by the given homework ID.
     * @param homeWorkId Integer
     * @return JSONObject containing the homework details.
     */
    @RequestMapping(path = "/getHomeWorkById/{homeWorkId}", method = RequestMethod.GET)
    public JSONObject getHomeWorkById(@PathVariable("homeWorkId") int homeWorkId){
        LOGGER.info("getHomeWorkById API called with homeWorkId={}", homeWorkId);
        return createResponse(Arrays.asList(homeWorkService.getHomeWorkById(homeWorkId)));
    }

    /**
     * Method returns the list of homeWorks by given
     * @param homeWorkNo Integer
     * @param courseId Integer
     * @return JSONObject containing the list of Homeworks
     */
    @RequestMapping(path = "/getListOfHomeWorkById/{courseId}/{homeWorkNo}", method = RequestMethod.GET)
    public JSONObject getListOfHomeWorkByCourseAndHomeWorkNo(@PathVariable("homeWorkNo") int homeWorkNo,
                                                             @PathVariable("courseId") int courseId){
        LOGGER.info("getListOfHomeWorkByCourseAndHomeWorkNo API called " +
                "with homeWorkId={} and courseId={}", homeWorkNo, courseId);
        return createResponse(homeWorkService.getHomeWorkByCourseIdAndHomeWorkNumber(courseId, homeWorkNo));
    }

    private JSONObject createResponse(List<HomeWork> homeWorkList){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", homeWorkList);
        resultMap.put("response-code", "OK");
        return new JSONObject(resultMap);
    }

}
