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
     * Method to get the list of instances of HomeWork for a given CourseId.
     * @param courseId Integer
     * @return List of Instance of HomeWork
     */
    List<HomeWork> findByCourseId(Integer courseId);

    /**
     * Method to get Instance of HomeWork based on the HomeWork Id.
     * @param homeWorkId Integer
     * @return Instance of HomeWork
     */
    HomeWork findById(Integer homeWorkId);

    /**
     * Method to get the list Instance of HomeWork based on courseNo and HomeWorkNo.
     * @param courseId Integer
     * @param homeWorkNumber Integer
     * @return List of Instance of HomeWork
     */
    List<HomeWork> findByCourseIdAndHomeWorkNumber(Integer courseId, Integer homeWorkNumber);
}
