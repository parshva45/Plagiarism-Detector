package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Praveen Singh
 */
@RestController
@RequestMapping("/api/course/")
public class CourseController {
    private static final Logger LOGGER = LogManager.getLogger(CourseController.class);

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(path = "/getCourseById/{courseId}", method = RequestMethod.GET)
    public JSONObject getCourseById(@PathVariable int courseId){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", courseService.getCourseById(courseId));
        resultMap.put("response-code", "OK");
        LOGGER.info("sent JSONObject as API Response");
        return new JSONObject(resultMap);
    }
}
