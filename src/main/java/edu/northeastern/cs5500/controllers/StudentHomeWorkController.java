package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.service.StudentHomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Praveen Singh
 */
@RestController
public class StudentHomeWorkController {
    private static final String ERROR_HEADER = "error";

    private final StudentHomeWorkService studentHomeWorkService;

    @Autowired
    public StudentHomeWorkController(StudentHomeWorkService studentHomeWorkService) {
        this.studentHomeWorkService = studentHomeWorkService;
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
