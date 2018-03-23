package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.strategies.StrategyFactory;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Praveen Singh
 * File Comparison Service Class. This has the methods required for file comparison
 * functionality, seperating the controller and the repository.
 */
@Service
public class FileComparisonService {
    private static final Logger LOGGER = LogManager.getLogger(FileComparisonService.class);

    private final StrategyFactory strategyFactory;

    @Autowired
    public FileComparisonService(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    /**
     * Method to get the similarity between 2 given files based on the given strategy
     * @param strategy ENUM
     * @param firstFile String location of first file on server
     * @param secondFile String location of second file on server
     * @return double similarity percentage out of 100%.
     */
    public double compareTwoFilesByGivenStrategy(String strategy, String firstFile, String secondFile){
        LOGGER.info("Running compareTwoFilesByGivenStrategy for the files {} {} with strategy {}", firstFile, secondFile, secondFile);
        return strategyFactory
                .getStrategyByStrategyType(strategy)
                .calculateSimilarity(firstFile, secondFile);
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
}
