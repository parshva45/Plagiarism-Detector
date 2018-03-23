package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.HomeWork;
import edu.northeastern.cs5500.repository.HomeWorkRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Praveen Singh
 * service Class for Homework, seperating homeWork controller and repository.
 */
@Service
public class HomeWorkService {
    private static final Logger LOGGER = LogManager.getLogger(HomeWorkService.class);

    private final HomeWorkRepository homeWorkRepository;

    @Autowired
    public HomeWorkService(HomeWorkRepository homeWorkRepository) {
        this.homeWorkRepository = homeWorkRepository;
    }

    /**
     * Method to get all the course for homework.
     * @param courseNo Integer CourseId
     * @return List of HomeWork
     */
    public List<HomeWork> getListOfHomeWorkByCourseNo(Integer courseNo){
        LOGGER.info("getListOfHomeWorkByCourseNo called with courseId {}", courseNo);
        return homeWorkRepository.findByCourseId(courseNo);
    }

    /**
     * Method to get homeWork for given Id.
     * @param homeWorkId Integer
     * @return HomeWork instance
     */
    public HomeWork getHomeWorkById(Integer homeWorkId){
        LOGGER.info("getHomeWorkById called with homeWorkId {}", homeWorkId);
        return homeWorkRepository.findById(homeWorkId);
    }

    /**
     * Method to get the homeworks for the given course Id and homeWork No.
     * @param courseId Integer Couser Id for the HomeWork
     * @param homeWorkNo Integer HomeWorkNo for the Homwork
     * @return List of homeWork for the given course and HomeWork No.
     */
    public List<HomeWork> getHomeWorkByCourseIdAndHomeWorkNumber(Integer courseId,
                                                                 Integer homeWorkNo){
        LOGGER.info("getHomeWorkByCourseIdAndHomeWorkNumber called with homeWorkId {}" +
                        " and courseId {}", homeWorkNo, courseId);
        return homeWorkRepository.findByCourseIdAndHomeWorkNumber(courseId, homeWorkNo);
    }

}
