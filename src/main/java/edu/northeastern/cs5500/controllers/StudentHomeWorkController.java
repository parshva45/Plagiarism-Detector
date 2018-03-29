package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.StudentHomeWorkService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Praveen Singh
 */
@RestController
@RequestMapping("/api/studentHomeWork")
public class StudentHomeWorkController {
    private static final Logger LOGGER = LogManager.getLogger(StudentHomeWorkController.class);

    private final StudentHomeWorkService studentHomeWorkService;

    @Autowired
    public StudentHomeWorkController(StudentHomeWorkService studentHomeWorkService) {
        this.studentHomeWorkService = studentHomeWorkService;
    }

    /**
     *
     * @param studentId Integer
     * @return
     */
    @RequestMapping(path = "/getHomeWorksForStudent/{studentId}", method = RequestMethod.GET)
    public JSONObject getHomeWorksForStudent(@PathVariable int studentId){
        LOGGER.info("getHomeWorksForStudent API called with studentId={}", studentId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", studentHomeWorkService.getListOfHomeWorksByStudentId(studentId));
        resultMap.put("response-code", "OK");
        return new JSONObject(resultMap);
    }

    @RequestMapping(path = "/submitHomeWork", method = RequestMethod.POST)
    public JSONObject submitHomeWork(@RequestParam("filePath") String uploadFile,
                                     @RequestParam("userId") int userId,
                                     @RequestParam("hwId") int hwId,
                                     @RequestParam("courseId") int courseId){
        LOGGER.info("getHomeWorksForStudent API called with studentId={} " +
                "homeworkId={} and courseId={}", userId, hwId, courseId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", studentHomeWorkService.submitHomeWork(userId, courseId, hwId, uploadFile));
        resultMap.put("response-code", "OK");
        return new JSONObject(resultMap);
    }


}
