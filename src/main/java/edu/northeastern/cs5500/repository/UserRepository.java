package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Praveen Singh
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserNameAndPassword(String username, String password);
    List<User> findByUserNameLike(String username);
    User findById(Integer id);
    boolean existsByUserName(String username);
}
