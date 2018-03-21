package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.StudentHomeWorkService;
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
@RequestMapping("/api/studentHomeWork")
public class StudentHomeWorkController {

    private final StudentHomeWorkService studentHomeWorkService;

    @Autowired
    public StudentHomeWorkController(StudentHomeWorkService studentHomeWorkService) {
        this.studentHomeWorkService = studentHomeWorkService;
    }

    @RequestMapping(path = "/getHomeWorksForStudent/{studentId}", method = RequestMethod.GET)
    public JSONObject getHomeWorksForStudent(@PathVariable int studentId){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", studentHomeWorkService.getListOfHomeWorksByStudentId(studentId));
        resultMap.put("response-code", "OK");
        return new JSONObject(resultMap);
    }




}
