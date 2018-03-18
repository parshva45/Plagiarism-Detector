package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Praveen Singh
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameAndPassword(String username, String password);
    List<User> findByUsernameLike(String username);
    User findById(Integer id);
    boolean existsByUsername(String username);
}
