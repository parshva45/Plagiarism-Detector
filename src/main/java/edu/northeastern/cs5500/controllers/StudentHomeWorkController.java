package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.service.StudentHomeWorkService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Praveen Singh
 */
@RestController
@RequestMapping("/api/studentHomeWork")
public class StudentHomeWorkController {
    private static final String ERROR_HEADER = "error";

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


    private ResponseEntity<StudentHomeWork> catchRequestExceptions(final int userId,
                final Map<String, String> homeWorkDetails,
                final BiFunction<Integer, Map<String, String>, StudentHomeWork> func) {
        try {
            return ResponseEntity.ok(func.apply(userId, homeWorkDetails));
        } catch (StudentHomeWorkService.HttpStatusException e) {
            return ResponseEntity.status(e.getStatus()).header(ERROR_HEADER, e.getMessage()).build();
        }
    }
}
