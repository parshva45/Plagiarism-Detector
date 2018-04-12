package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.StudentHomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Praveen Singh
 * JPA Repository class for the StudentHomeWork table related queries.
 */
@Repository
public interface StudentHomeWorkRepository extends JpaRepository<StudentHomeWork, Integer> {
    /**
     * Method to find the list of homeworks submitted by a user for a given course
     * and given homework
     * @param userId Integer
     * @param courseId Integer
     * @param homeWorkId Integer
     * @return List of StudentHomeWork Objects
     */
    List<StudentHomeWork> findByUserIdAndCourseIdAndHomeWorkId(Integer userId,
                                                               Integer courseId,
                                                               Integer homeWorkId);

    /**
     * Method finds all the StudentHomeWork rows from the database for a given user.
     * @param userId Integer userId of the student
     * @return List of StudentHomeWork object
     */
    List<StudentHomeWork> findAllByUserId(Integer userId);

    /**
     * Method finds all the StudentHomeWork objects for the given course, homework no
     * @param courseId Integer Id of the course
     * @param homeWorkId Integer Id of the homework
     * @return List of StudentHomeWork object
     */
    List<StudentHomeWork> findAllByCourseIdAndAndHomeWorkId(Integer courseId, Integer homeWorkId);


    @Modifying
    @Query("update StudentHomeWork sh set sh.filePath = ?1 where sh.id = ?2")
    void updateHomeWorkPath(String filePath,  Integer id);
}
