package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.StudentHomeWorkService;
import edu.northeastern.cs5500.utils.Constants;
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
        resultMap.put(Constants.RESULT, studentHomeWorkService.getListOfHomeWorksByStudentId(studentId));
        resultMap.put(Constants.RESPONSE_CODE, "OK");
        return new JSONObject(resultMap);
    }
    
    /**
    *
    * @param courseId Integer
    * @param homeWorkId Integer
    * @return
    */
   @RequestMapping(path = "/getStudentHomeWorksForCourseHomeWork/{courseId}/{homeWorkId}", method = RequestMethod.GET)
   public JSONObject getStudentHomeWorksForCourseHomeWork(@PathVariable("courseId") int courseId,
		   												  @PathVariable("homeWorkId") int homeWorkId){
       LOGGER.info("getStudentHomeWorksForCourseHomeWork API called with courseId={} and homeWorkId={}", courseId, homeWorkId);
       Map<String, Object> resultMap = new HashMap<>();
       resultMap.put(Constants.RESULT, studentHomeWorkService.getListOfHomeWorksByCourseIdAndHomeWorkId(courseId, homeWorkId));
       resultMap.put(Constants.RESPONSE_CODE, "OK");
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
        resultMap.put(Constants.RESULT, studentHomeWorkService.submitHomeWork(userId, courseId, hwId, uploadFile));
        resultMap.put(Constants.RESPONSE_CODE, "OK");
        return new JSONObject(resultMap);
    }


}
