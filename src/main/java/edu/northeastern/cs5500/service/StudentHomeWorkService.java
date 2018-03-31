package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.repository.StudentHomeWorkRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Praveen Singh
 * StudentHomeWork service class to seperate the controller and Repository
 */
@Service
public class StudentHomeWorkService {
    private static final Logger LOGGER = LogManager.getLogger(StudentHomeWorkService.class);

    private final StudentHomeWorkRepository studentHomeWorkRepository;

    @Autowired
    public StudentHomeWorkService(StudentHomeWorkRepository studentHomeWorkRepository) {
        this.studentHomeWorkRepository = studentHomeWorkRepository;
    }

    /**
     * Method returs List of StudentHomeWork instances based on userId
     * @param userId Integer
     * @return List of StudentHomeWork instances
     */
    public List<StudentHomeWork> getListOfHomeWorksByStudentId(Integer userId){
        LOGGER.info("getting lis of homeworks for user {}", userId);
        return studentHomeWorkRepository.findAllByUserId(userId);
    }

    /**
     * methof returns a list of List of StudentHomeWork instances based on
     * CourseId and homeWorkId.
     * @param courseId integer
     * @param homeWorkId Integer
     * @return List of StudentHomeWork instances
     */
    public List<StudentHomeWork> getListOfHomeWorksByCourseIdAndHomeWorkId(Integer courseId,
              Integer homeWorkId){
        LOGGER.info("getting lis of homeworks for course {} and homeworkNo",
                homeWorkId, courseId);
        return studentHomeWorkRepository.findAllByCourseIdAndAndHomeWorkId(courseId, homeWorkId);
    }

    /**
     * Method gets the list of homwork files submitted for a used based on userId, homeworkNo
     * and CourseId.
     * @param userId Integer
     * @param courseId Integer
     * @param homeWorkId Integer
     * @return List of StudentHomeWork instances
     */
    public List<StudentHomeWork> getHomeWorkByStudentIdCourseIdAndHomeWorkId(Integer userId,
                   Integer courseId, Integer homeWorkId){
        LOGGER.info("uploading homework for user {}", userId);
        List<StudentHomeWork> studentHomeWork = studentHomeWorkRepository
                .findByUserIdAndAndCourseIdAndHomeWorkId(userId, courseId, homeWorkId);
        if (studentHomeWork == null){
            LOGGER.info("homework not found for user {} with courseID {} " +
                    "and homeWorkId {}", userId, courseId, homeWorkId);
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "User did not submit assignment.");
        }
        return studentHomeWork;
    }

    /**
     * This method saves and returns the StudentHomeWork instance based on userId, courseId and
     * homeWork Id, and path.
     * @param userId Integer
     * @param courseId courseId
     * @param homeWorkId Integer homeworkNo
     * @param path String
     * @return Instance of StudentHomeWork based on given input
     */
    public StudentHomeWork submitHomeWork(Integer userId, Integer courseId, Integer homeWorkId, String path){
        StudentHomeWork studentHomeWork = new StudentHomeWork()
                .withHomeWorkId(homeWorkId)
                .withCourseId(courseId)
                .withUserId(userId)
                .withPath(path);
        studentHomeWorkRepository.save(studentHomeWork);
        return studentHomeWork;
    }

    /**
     * Custom HTTP Exception class.
     */
    public static class HttpStatusException extends RuntimeException {
        private final HttpStatus status;

        /**
         * Custom HTTP Exception for constructor
         * @param status HttpStatus
         * @param message String
         */
        HttpStatusException(final HttpStatus status, final String message) {
            super(message);
            this.status = status;
        }
    }
}
