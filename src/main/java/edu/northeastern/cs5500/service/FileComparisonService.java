package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.SystemStatus;
import edu.northeastern.cs5500.repository.SystemStatusRepository;
import edu.northeastern.cs5500.strategies.StrategyFactory;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Praveen Singh
 * File Comparison Service Class. This has the methods required for file comparison
 * functionality, seperating the controller and the repository.
 */
@Service
public class FileComparisonService {
    private static final Logger LOGGER = LogManager.getLogger(FileComparisonService.class);

    private final StrategyFactory strategyFactory;
    private final SystemStatusRepository systemStatusRepository;

    @Autowired
    public FileComparisonService(StrategyFactory strategyFactory,
                                 SystemStatusRepository systemStatusRepository) {
        this.strategyFactory = strategyFactory;
        this.systemStatusRepository = systemStatusRepository;
    }

    /**
     * Method to get the similarity between 2 given files based on the given strategy
     * @param strategy ENUM
     * @param firstFile String location of first file on server
     * @param secondFile String location of second file on server
     * @return double similarity percentage out of 100%.
     */
    public double compareTwoFilesByGivenStrategy(String strategy, String firstFile, String secondFile){
        LOGGER.info("Running compareTwoFilesByGivenStrategy for the files {} {} with strategy {}", firstFile, secondFile, strategy);
        double score =  strategyFactory
                .getStrategyByStrategyType(strategy)
                .calculateSimilarity(firstFile, secondFile);
        SystemStatus systemStatus = new SystemStatus().withCourse(1)
                .withHomeWorkId(1).withProfessorId(1).withStrategy(strategy)
                .withScore(score);
        systemStatusRepository.save(systemStatus);
        return score;
    }

    public int[][] findLineNumbersByGivenStrategy(String strategy, String firstFile, String secondFile){
        LOGGER.info("Running findLineNumbersByGivenStrategy for the files {} {} with strategy {}", firstFile, secondFile, strategy);
        int[][] lineNumbers =  strategyFactory
                .getStrategyByStrategyType(strategy)
                .getsimilarLineNos(firstFile, secondFile);
        /*SystemStatus systemStatus = new SystemStatus().withCourse(1)
                .withHomeWorkId(1).withProfessorId(1).withStrategy(strategy)
                .withScore(score);
        systemStatusRepository.save(systemStatus);*/
        return lineNumbers;
    }



    /**
     * Method to get the list of strategies present in the system
     * @return List of strategies in Strinf.
     */
    public List<StrategyTypes> getAllStrategies(){
        List<StrategyTypes> strategyTypes;
        strategyTypes = new ArrayList<>(EnumSet.allOf(StrategyTypes.class));
        return strategyTypes;
    }

    /**
     * Method returns the count of plagiarism detection tasks run.
     * @return int count
     */
    public int getCount(){
        List<SystemStatus> systemStatuses = systemStatusRepository.findAllByCourseId(1);
        return systemStatuses.size();
    }

    public Map<String, Double> getSimilarityByAllMethods(String firstFile, String secondFile){
        Map<String, Double> res = new HashMap<>();
        List<StrategyTypes> strategyTypes = new ArrayList<>(EnumSet.allOf(StrategyTypes.class));
        for (StrategyTypes strategyType:strategyTypes) {
            res.put(strategyType.toString(),
                    strategyFactory
                    .getStrategyByStrategyType(strategyType.toString())
                    .calculateSimilarity(firstFile, secondFile));
        }
        return res;
    }
}
