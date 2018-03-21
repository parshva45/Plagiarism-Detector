package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Praveen Singh
 */
@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWork, Integer> {
    List<HomeWork> findByCourseId(Integer courseId);
    HomeWork findById(Integer homeWorkId);
    List<HomeWork> findByCourseIdAndHomeWorkNumber(Integer courseId, Integer homeWorkNumber);
}
