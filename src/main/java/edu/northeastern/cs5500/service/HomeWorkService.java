package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.HomeWork;
import edu.northeastern.cs5500.repository.HomeWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Praveen Singh
 */
@Service
public class HomeWorkService {

    private final HomeWorkRepository homeWorkRepository;

    @Autowired
    public HomeWorkService(HomeWorkRepository homeWorkRepository) {
        this.homeWorkRepository = homeWorkRepository;
    }

    public List<HomeWork> getListOfHomeWorkByCourseNo(Integer courseNo){
        return homeWorkRepository.findByCourseId(courseNo);
    }

    public HomeWork getHomeWorkById(Integer homeWorkId){
        return homeWorkRepository.findById(homeWorkId);
    }

    public List<HomeWork> getHomeWorkByCourseIdAndHomeWorkNumber(Integer courseId,
                                                                 Integer homeWorkNo){
        return homeWorkRepository.findByCourseIdAndHomeWorkNumber(courseId, homeWorkNo);
    }

}
