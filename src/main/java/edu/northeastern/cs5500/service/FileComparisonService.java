package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.strategies.StrategyFactory;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Praveen Singh
 */
@Service
public class FileComparisonService {

    private final StrategyFactory strategyFactory;

    @Autowired
    public FileComparisonService(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public double compareTwoFilesByGivenStrategy(String strategy, String firstFile, String secondFile){
        return strategyFactory
                .getStrategyByStrategyType(strategy)
                .calculateSimilarity(firstFile, secondFile);
    }

    public List<StrategyTypes> getAllStrategies(){
        List<StrategyTypes> strategyTypes;
        strategyTypes = new ArrayList<>(EnumSet.allOf(StrategyTypes.class));
        return strategyTypes;
    }
}
