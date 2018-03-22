package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Praveen Singh
 * HomeWork repository class
 */
@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWork, Integer> {

    /**
     *
     * @param courseId
     * @return
     */
    List<HomeWork> findByCourseId(Integer courseId);

    /**
     *
     * @param homeWorkId
     * @return
     */
    HomeWork findById(Integer homeWorkId);

    /**
     *
     * @param courseId
     * @param homeWorkNumber
     * @return
     */
    List<HomeWork> findByCourseIdAndHomeWorkNumber(Integer courseId, Integer homeWorkNumber);
}
