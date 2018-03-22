package edu.northeastern.cs5500.repository;

import edu.northeastern.cs5500.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Praveen Singh
 * Database JPA repository for the user table
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Gets a List of users from the database based on UserName and Password
     * @param username String UserName Given
     * @param password String pass word provided
     * @return a list of users matching the given criteria.
     */
    List<User> findByUserNameAndPassword(String username, String password);

    /**
     * Method finds  List of users having username as given string
     * @param username String
     * @return List of users
     */
    List<User> findByUserNameLike(String username);

    /**
     * Method gets a user from the database based on the given userId.
     * @param id Integer userId
     * @return an Instance of a User Object
     */
    User findById(Integer id);

    /**
     * returns true if a user with the given username exists in the database.
     * @param username String given username
     * @return boolean
     */
    boolean existsByUserName(String username);
}
