package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.HomeWork;
import edu.northeastern.cs5500.service.HomeWorkService;
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

    private final HomeWorkService homeWorkService;

    @Autowired
    public HomeWorkController(HomeWorkService homeWorkService) {
        this.homeWorkService = homeWorkService;
    }

    @RequestMapping(path = "/getHomeWorksByCourse/{courseId}", method = RequestMethod.GET)
    public JSONObject getListOfHomeWorksByCourse(@PathVariable int courseId){
        return createResponse(homeWorkService.getListOfHomeWorkByCourseNo(courseId));
    }

    @RequestMapping(path = "/getHomeWorkById/{homeWorkId}", method = RequestMethod.GET)
    public JSONObject getHomeWorkById(@PathVariable("homeWorkId") int homeWorkId){
        return createResponse(Arrays.asList(homeWorkService.getHomeWorkById(homeWorkId)));
    }

    @RequestMapping(path = "/getListOfHomeWorkById/{courseId}/{homeWorkNo}", method = RequestMethod.GET)
    public JSONObject getListOfHomeWorkByCourseAndHomeWorkNo(@PathVariable("homeWorkNo") int homeWorkNo,
                                                             @PathVariable("courseId") int courseId){
        return createResponse(homeWorkService.getHomeWorkByCourseIdAndHomeWorkNumber(courseId, homeWorkNo));
    }

    private JSONObject createResponse(List<HomeWork> homeWorkList){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", homeWorkList);
        resultMap.put("response-code", "OK");
        return new JSONObject(resultMap);
    }

}
