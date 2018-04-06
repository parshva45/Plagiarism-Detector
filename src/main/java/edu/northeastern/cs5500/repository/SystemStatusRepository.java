package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.SystemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Praveen Singh
 */
public interface SystemStatusRepository extends JpaRepository<SystemStatus, Integer> {
    List<SystemStatus> findAllByCourseId(int courseId);
}
