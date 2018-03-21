package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.repository.StudentHomeWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Praveen Singh
 */
@Service
public class StudentHomeWorkService {

    private final StudentHomeWorkRepository studentHomeWorkRepository;

    @Autowired
    public StudentHomeWorkService(StudentHomeWorkRepository studentHomeWorkRepository) {
        this.studentHomeWorkRepository = studentHomeWorkRepository;
    }

    public List<StudentHomeWork> getListOfHomeWorksByStudentId(Integer userId){
        return studentHomeWorkRepository.findAllByUserId(userId);
    }

    public List<StudentHomeWork> getListOfHomeWorksByCourseIdAndHomeWorkId(Integer courseId,
              Integer homeWorkId){
        return studentHomeWorkRepository.findAllByCourseIdAndAndHomeWorkId(courseId, homeWorkId);
    }

    public StudentHomeWork getHomeWorkByStudentIdCourseIdAndHomeWorkId(Integer userId,
                   Integer courseId, Integer homeWorkId){
        StudentHomeWork studentHomeWork = studentHomeWorkRepository
                .findByUserIdAndAndCourseIdAndHomeWorkId(userId, courseId, homeWorkId);
        if (studentHomeWork == null){
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "User did not submit assignment.");
        }
        return studentHomeWork;
    }

    public static class HttpStatusException extends RuntimeException {
        private final HttpStatus status;

        HttpStatusException(final HttpStatus status, final String message) {
            super(message);
            this.status = status;
        }

        public HttpStatus getStatus() {
            return status;
        }
    }
}
